package com.kaba.tutorials.redis;

    import lombok.AllArgsConstructor;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
    import org.springframework.data.redis.core.ReactiveRedisOperations;
    import org.springframework.stereotype.Component;
    import reactor.core.publisher.Flux;

    import java.util.UUID;

@Component
@AllArgsConstructor
public class CapsuleLoader implements CommandLineRunner {

    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, Capsule> capsulOperations;

    @Override
    public void run(String... strings) {
        factory.getReactiveConnection().serverCommands().flushAll().thenMany(
            Flux.just("Capsule 1", "Capsule 2", "Capsule 3")
                .map(content -> new Capsule(UUID.randomUUID().toString(), content))
                .flatMap(capsule -> capsulOperations.opsForValue().set(capsule.getName(), capsule)))
            .thenMany(capsulOperations.keys("*")
                .flatMap(capsulOperations.opsForValue()::get))
            .subscribe();
    }

}
