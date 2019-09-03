package com.example.demo.core;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CacheTemplate<T> {
    String getNode(String key);
    Flux<T> mGet(List<String> keyList);
    Mono<T> get(String key);
    Mono<Void> save(T o);
}