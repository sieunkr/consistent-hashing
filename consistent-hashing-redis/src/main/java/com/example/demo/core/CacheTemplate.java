package com.example.demo.core;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface CacheTemplate<T> {
    //Key 가 저장되어야 하는 노드의 이름을 조회
    String getNode(String key);

    //KeyList 가 저장되어야 하는 노드맵 조회
    Map<String, String> getNodeMap(List<String> keyList);

    //Key 리스트로 데이터 리스트를 조회
    Flux<T> mGet(List<String> keyList);

    //Key 로 단일 데이터 조회
    Mono<T> get(String key);

    //신규 데이터 저장
    Mono<Void> save(T o);

    Boolean isSameSlot(List<String> keyList);
}
