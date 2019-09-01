package com.example.demo.core;

import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

public interface CacheTemplate {
    //void init(List<String> nodeInfo);
    String getNode(String key);
    Mono<Person> get(String key);
    Mono<Void> save(Object o);
}
