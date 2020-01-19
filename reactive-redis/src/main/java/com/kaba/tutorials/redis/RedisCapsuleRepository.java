package com.kaba.tutorials.redis;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author KABA N'faly
 * @since 18/01/2020 12:33
 */
@AllArgsConstructor
public class RedisCapsuleRepository implements CapsuleRepository {
  private final ReactiveRedisOperations<String, Capsule> capsuleOperations;

  @Override
  public Mono<Capsule> findById(String id) {
    return capsuleOperations.opsForValue().get(id);
  }

  @Override
  public Flux<Capsule> findAll() {
    return capsuleOperations.keys("*")
        .flatMap(capsuleOperations.opsForValue()::get);
  }

  @Override
  public Mono<Capsule> save(Capsule capsule) {
    return Mono.empty();
//    return capsuleOperations.opsForValue().set(capsule.getName(), capsule)
//        .flatMap(saved -> {
//          if(saved){
//            return Mono.just(capsule);
//          }
//          return Mono.empty();
//        });
  }
}
