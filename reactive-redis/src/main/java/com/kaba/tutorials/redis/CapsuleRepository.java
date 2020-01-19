package com.kaba.tutorials.redis;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author KABA N'faly
 * @since 18/01/2020 12:48
 */
public interface CapsuleRepository {
  Mono<Capsule> findById(String id);
  Flux<Capsule> findAll();
  Mono<Capsule> save(Capsule capsule);
}
