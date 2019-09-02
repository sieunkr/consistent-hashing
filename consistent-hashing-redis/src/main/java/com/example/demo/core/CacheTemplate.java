package com.example.demo.core;

import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

public interface CacheTemplate {
    //void init(List<String> nodeInfo);
    String getNode(String key);
    //TODO:제네릭 으로 변경..
    Mono<Person> get(String key);
    Mono<Void> save(Object o);
}
