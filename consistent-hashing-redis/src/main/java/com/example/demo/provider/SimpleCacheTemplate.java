package com.example.demo.provider;

import com.example.demo.core.CacheRepository

        ;
import com.example.demo.core.CacheTemplate;
import com.example.demo.core.Person;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SimpleCacheTemplate implements CacheTemplate<Person> {

    private Map<Integer, CacheRepository> nodeMap = new HashMap<>();
    private static int BUCKETS_SIZE = 1024;
    private String firstNode;
    private List<String> virtualNodes;

    private final Map<String, ReactiveRedisTemplate<String, Person>> reactiveRedisTemplateMap;

    @PostConstruct
    public void init(){
        
        virtualNodes = Arrays.asList(
                ///*
                "reactiveRedisTemplateNodeA-01", "reactiveRedisTemplateNodeB-01", "reactiveRedisTemplateNodeC-01"

                ,
                "reactiveRedisTemplateNodeA-02", "reactiveRedisTemplateNodeB-02", "reactiveRedisTemplateNodeC-02",
                "reactiveRedisTemplateNodeA-03", "reactiveRedisTemplateNodeB-03", "reactiveRedisTemplateNodeC-03",
                "reactiveRedisTemplateNodeA-04", "reactiveRedisTemplateNodeB-04", "reactiveRedisTemplateNodeC-04",
                "reactiveRedisTemplateNodeA-05", "reactiveRedisTemplateNodeB-05", "reactiveRedisTemplateNodeC-05"
                 //*/
        );

        //TODO: 서버 노드 버킷이 중복되는 경우에는 어떻게 처리되는가?
        virtualNodes.forEach(n -> nodeMap.put(getBucketByHashCode(n), new CacheRepository(n)));

        firstNode = nodeMap.get(nodeMap.keySet().stream().sorted().findFirst().get()).getNodeName();
    }

    @Override
    public String getNode(String key){
        //TODO: 성능개선, 알고리즘 개선 필요
        return nodeMap.keySet().stream()
                .sorted()
                .filter(k -> k >= getBucketByHashCode(key))
                .findFirst()
                .map(integer -> nodeMap.get(integer).getNodeName())
                .orElse(firstNode);
    }

    @Override
    public Mono<Person> get(String key) {
        return getRedisTemplate(key).opsForValue().get(key);
    }

    @Override
    public Mono<Void> save(Person p) {
        getRedisTemplate(p.getName()).opsForValue().set(p.getName(), p).subscribe();
        return Mono.empty();
    }



    public int getBucket(String key){
        return getBucketByHashCode(key);
    }

    public List<String> getNodes(){
        return virtualNodes.stream()
                .map(n -> n + "-" + getBucket(n))
                .collect(Collectors.toList());
    }

    private ReactiveRedisTemplate<String, Person> getRedisTemplate(String key){
        return reactiveRedisTemplateMap.get(getNode(key).split("-")[0]);
    }

    private int getBucketByHashCode(String s){
        return Hashing.consistentHash(Hashing.sha256().hashString(s, Charsets.UTF_8), BUCKETS_SIZE);
    }
}