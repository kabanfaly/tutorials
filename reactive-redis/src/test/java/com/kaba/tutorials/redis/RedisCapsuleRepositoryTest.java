package com.kaba.tutorials.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import reactor.test.StepVerifier;

class RedisCapsuleRepositoryTest extends RedisTestConfig {

  private ReactiveRedisOperations<String, Capsule> reactiveRedisOperations;
  private LettuceConnectionFactory connectionFactory;
  private CapsuleRepository capsuleRepository;

  @BeforeEach
  public void setup() {
    connectionFactory = new LettuceConnectionFactory("localhost", redisPort);
    connectionFactory.afterPropertiesSet(); // enable connection
    reactiveRedisOperations = redisOperations(connectionFactory);
    capsuleRepository = new RedisCapsuleRepository(reactiveRedisOperations);

    // delete all
    reactiveRedisOperations.keys("*").flatMap(reactiveRedisOperations.opsForValue()::delete).subscribe();
  }

  public ReactiveRedisOperations<String, Capsule> redisOperations(ReactiveRedisConnectionFactory factory) {
    Jackson2JsonRedisSerializer<Capsule> serializer = new Jackson2JsonRedisSerializer<>(Capsule.class);

    RedisSerializationContext.RedisSerializationContextBuilder<String, Capsule> builder
        = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

    RedisSerializationContext<String, Capsule> context = builder.value(serializer).build();

    return new ReactiveRedisTemplate<>(factory, context);
  }

  @DisplayName("Should find capsule when name exists")
  @Test
  void findByIdFound() {
    // Given
    Capsule capsule = new Capsule("name1", "content1");
    reactiveRedisOperations.opsForValue().set("name1", capsule).subscribe(); // don't miss 'subscribe' to persist data

    StepVerifier.create(capsuleRepository.findById("name1"))
        .expectNext(capsule)
        .expectComplete()
        .verify();
  }

  @DisplayName("Should find no capsule when name does not exist")
  @Test
  void findByIdNotFound() {
    // Given
    StepVerifier.create(capsuleRepository.findById("NON_EXISTING_ID"))
        .expectNextCount(0)
        .expectComplete()
        .verify();
  }

  @DisplayName("Should find all persisted capsules")
  @Test
  void findAll() {
    // Given
    Capsule capsule1 = new Capsule("name1", "content1");
    reactiveRedisOperations.opsForValue().set("name1", capsule1).subscribe(); // don't miss 'subscribe' to persist data
    Capsule capsule2 = new Capsule("name2", "content2");
    reactiveRedisOperations.opsForValue().set("name2", capsule2).subscribe();

    StepVerifier.create(capsuleRepository.findAll())
        .assertNext(capsule -> {
          capsule.getName().equals(capsule1.getName());
          capsule.getName().equals(capsule1.getContent());
        })
        .assertNext(capsule -> {
          capsule.getName().equals(capsule2.getName());
          capsule.getName().equals(capsule2.getContent());
        })
        .expectComplete()
        .verify();
  }

  @DisplayName("Should save capsule")
  @Test
  void save() {
    Capsule capsule = new Capsule("name", "content1");
    StepVerifier.create(capsuleRepository.save(capsule))
        .expectNext(capsule)
        .expectComplete()
        .verify();
  }
}