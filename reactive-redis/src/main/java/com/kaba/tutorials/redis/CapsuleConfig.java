package com.kaba.tutorials.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.RedisSerializationContextBuilder;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static org.springframework.data.redis.serializer.RedisSerializationContext.newSerializationContext;

@Configuration
public class CapsuleConfig {

    @Bean
    public CapsuleRepository capsuleRepository(ReactiveRedisOperations<String, Capsule> capsuleOperations) {
        return new RedisCapsuleRepository(capsuleOperations);
    }

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory connectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory("localhost", 6379);
        return lettuceConnectionFactory;
    }

    @Bean
    public ReactiveRedisOperations<String, Capsule> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Capsule> serializer = new Jackson2JsonRedisSerializer<>(Capsule.class);

        RedisSerializationContextBuilder<String, Capsule> builder = newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, Capsule> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
