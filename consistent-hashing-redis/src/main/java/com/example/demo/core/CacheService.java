package com.example.demo.core;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CacheService {

    private final CacheTemplate simpleCacheTemplate;

    public CacheService(CacheTemplate simpleCacheTemplate) {
        this.simpleCacheTemplate = simpleCacheTemplate;
    }

    public Mono<?> get(String key){
        return simpleCacheTemplate.get(key);
    }
}