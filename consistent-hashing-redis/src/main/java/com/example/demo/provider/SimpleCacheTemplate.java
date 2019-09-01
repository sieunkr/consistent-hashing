package com.example.demo.provider;

import com.example.demo.core.CacheRepository;
import com.example.demo.core.CacheTemplate;
import com.example.demo.core.Person;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleCacheTemplate implements CacheTemplate {

    private Map<Integer, CacheRepository> nodeMap = new HashMap<>();
    private static int BUCKETS_SIZE = 1024;
    private String firstNode;

    @Autowired
    private Map<String, ReactiveRedisTemplate<String, Person>> reactiveRedisTemplateMap;

    @PostConstruct
    public void init(){
        Arrays.asList("reactiveRedisTemplateNodeA", "reactiveRedisTemplateNodeB", "reactiveRedisTemplateNodeC")
                .forEach(n -> nodeMap.put(getBucketByHashCode(n), new CacheRepository(n)));
        firstNode = nodeMap.get(nodeMap.keySet().stream().sorted().findFirst().get()).getNodeName();
    }

    @Override
    public String getNode(String key){
        return nodeMap.keySet().stream()
                .sorted()
                .filter(k -> k >= getBucketByHashCode(key))
                .findFirst()
                .map(integer -> nodeMap.get(integer).getNodeName())
                .orElse(firstNode);
    }

    public int getBucket(String key){
        return getBucketByHashCode(key);
    }

    @Override
    public Mono<Person> get(String key) {
        //클라이언트에서 타겟 노드를 검사

        return getRedisTemplate(key).opsForValue().get(key);
        //return nodeMap.get(getInfo(key)).get(key);
    }

    @Override
    public Mono<Void> save(Object o) {
        Person p = (Person)o;
        getRedisTemplate(p.getName()).opsForValue().set(p.getName(), p).subscribe();
        return Mono.empty();
    }

    private ReactiveRedisTemplate<String, Person> getRedisTemplate(String key){
        return reactiveRedisTemplateMap.get(getNode(key));
    }

    private int getBucketByHashCode(String s){
        return Hashing.consistentHash(Hashing.sha256().hashString(s, Charsets.UTF_8), BUCKETS_SIZE);
    }
}