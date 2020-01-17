package com.kaba.tutorials.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CapsuleConfig {

    @Bean
    ReactiveRedisOperations<String, Capsule> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Capsule> serializer = new Jackson2JsonRedisSerializer<>(Capsule.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Capsule> builder
            = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Capsule> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
