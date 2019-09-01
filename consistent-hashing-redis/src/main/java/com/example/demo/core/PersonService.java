package com.example.demo.core;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {

    @Qualifier("reactiveRedisTemplateNodeB")
    private final ReactiveRedisOperations<String, Person> reactiveRedisTemplateNodeB;

    private final CacheTemplate simpleCacheTemplate;

    public Flux<Person> findAll(){

        return reactiveRedisTemplateNodeB.keys("eddy")
                .flatMap(reactiveRedisTemplateNodeB.opsForValue()::get);
    }

    public Mono<Person> findByKey(String name){
        return simpleCacheTemplate.get(name);
    }

    public Mono<Void> save(Person person){
        simpleCacheTemplate.save(person).subscribe().dispose();
        return Mono.empty();
    }
}
