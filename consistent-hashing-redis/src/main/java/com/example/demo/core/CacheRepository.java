package com.example.demo.core;

import java.util.HashMap;
import java.util.Map;

//
public class CacheRepository {
    private String nodeName;
    private Map<String, String> dataMap;

    private CacheRepository(){
    }

    public CacheRepository(String nodeName){
        this.nodeName = nodeName;
        dataMap = new HashMap<>();
    }

    public String getNodeName(){
        return nodeName;
    }

    public String get(String key){
        return dataMap.get(key);
    }
}