package com.example.demo.core;

import reactor.core.publisher.Mono;

public interface CacheTemplate<T> {
    String getNode(String key);
    Mono<T> get(String key);
    Mono<Void> save(T o);
}