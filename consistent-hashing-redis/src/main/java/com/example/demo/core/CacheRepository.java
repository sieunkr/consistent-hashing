package com.example.demo.core;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CacheRepository {
    private String nodeName;
    private Map<String, String> dataMap;

    private CacheRepository(){
    }

    public CacheRepository(String nodeName){
        this.nodeName = nodeName;
        dataMap = new HashMap<>();
    }
}