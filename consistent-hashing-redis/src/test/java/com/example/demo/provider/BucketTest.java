package com.example.demo.provider;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;

public class BucketTest {

    @Test
    public void 버킷_테스트(){

        int redisA = Hashing.consistentHash(
                Hashing.sha256().hashString("reactiveRedisTemplateNodeA", Charsets.UTF_8), 1024);

        int redisB = Hashing.consistentHash(
                Hashing.sha256().hashString("reactiveRedisTemplateNodeB", Charsets.UTF_8), 1024);

        int redisC = Hashing.consistentHash(
                Hashing.sha256().hashString("reactiveRedisTemplateNodeC", Charsets.UTF_8), 1024);

        System.out.println("노드 A 의 버킷 번호는 : " + redisA);
        System.out.println("노드 B 의 버킷 번호는 : " + redisB);
        System.out.println("노드 C 의 버킷 번호는 : " + redisC);

    }

}
