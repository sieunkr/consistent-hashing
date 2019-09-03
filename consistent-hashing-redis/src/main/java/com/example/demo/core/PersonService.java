package com.example.demo.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final CacheTemplate simpleCacheTemplate;

    public Mono<Person> findByKey(String name){

        //TODO:Look Aside Pattern
        return simpleCacheTemplate.get(name);
    }

    public Mono<Void> save(Person person){
        simpleCacheTemplate.save(person).subscribe().dispose();
        return Mono.empty();
    }
}
