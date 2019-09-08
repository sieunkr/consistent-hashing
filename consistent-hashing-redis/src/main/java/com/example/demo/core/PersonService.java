package com.example.demo.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final CacheTemplate<Person> simpleCacheTemplate;

    public Mono<Person> findByKey(String name){
        //TODO:Look Aside Pattern
        return simpleCacheTemplate.get(name);
    }
    
    public Flux<Person> findByKeyList(List<String> keyList){
        //TODO:Look Aside Pattern
        return simpleCacheTemplate.mGet(keyList);
    }

    public Mono<Void> save(Person person){
        simpleCacheTemplate.save(person).subscribe().dispose();
        return Mono.empty();
    }
}
