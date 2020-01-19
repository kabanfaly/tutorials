package com.kaba.tutorials.redis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@AllArgsConstructor
public class CapsuleController {

    private final CapsuleRepository capsuleRepository;

    @RequestMapping(value = "/capsules", method = GET)
    public Flux<Capsule> getAllCapsule() {
        return capsuleRepository.findAll();
    }

    @RequestMapping(value = "/capsule/{name}", method = GET)
    public Mono<Capsule> getCapsule(@PathVariable("name") String name) {
        return capsuleRepository.findById(name);
    }

    @RequestMapping(value = "/capsule", method = PUT)
    public Mono<Capsule> saveCapsule(@RequestBody Capsule capsule) {
        return capsuleRepository.save(capsule);
    }
}
