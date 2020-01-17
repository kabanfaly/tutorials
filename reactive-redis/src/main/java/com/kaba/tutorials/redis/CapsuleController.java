package com.kaba.tutorials.redis;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@AllArgsConstructor
public class CapsuleController {

    private final ReactiveRedisOperations<String, Capsule> capsulOperations;

    @RequestMapping(value = "/capsules", method = GET)
    public Flux<Capsule> all() {
        return capsulOperations.keys("*")
            .flatMap(capsulOperations.opsForValue()::get);
    }
}
