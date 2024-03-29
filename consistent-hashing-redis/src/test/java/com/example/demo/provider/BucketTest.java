package com.example.demo.provider;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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



    @Test
    public void 버킷_테스트_가상노드(){

        List<String> virtualNodes = Arrays.asList(
                //"reactiveRedisTemplateNodeA", "reactiveRedisTemplateNodeB", "reactiveRedisTemplateNodeC"
                "reactiveRedisTemplateNodeA-01", "reactiveRedisTemplateNodeB-01", "reactiveRedisTemplateNodeC-01",
                "reactiveRedisTemplateNodeA-02", "reactiveRedisTemplateNodeB-02", "reactiveRedisTemplateNodeC-02",
                "reactiveRedisTemplateNodeA-03", "reactiveRedisTemplateNodeB-03", "reactiveRedisTemplateNodeC-03",
                "reactiveRedisTemplateNodeA-04", "reactiveRedisTemplateNodeB-04", "reactiveRedisTemplateNodeC-04",
                "reactiveRedisTemplateNodeA-05", "reactiveRedisTemplateNodeB-05", "reactiveRedisTemplateNodeC-05"
        );

        for (String r : virtualNodes) {
            System.out.println("노드 " + r + " 의 버킷 번호는 : " + getBucket(r));
        }
    }

    private int getBucket(String key){
        return Hashing.consistentHash(
                Hashing.sha256().hashString(key, Charsets.UTF_8), 1024);
    }

    @Test
    public void 키_테스트(){

        int data01 = Hashing.consistentHash(
                Hashing.sha256().hashString("test:1", Charsets.UTF_8), 1024);

        int data02 = Hashing.consistentHash(
                Hashing.sha256().hashString("test:2", Charsets.UTF_8), 1024);

        int data03 = Hashing.consistentHash(
                Hashing.sha256().hashString("test:3", Charsets.UTF_8), 1024);

        int data04 = Hashing.consistentHash(
                Hashing.sha256().hashString("test:4", Charsets.UTF_8), 1024);

        int data05 = Hashing.consistentHash(
                Hashing.sha256().hashString("test:5", Charsets.UTF_8), 1024);

        System.out.println("test:1 의 버킷 번호는 : " + data01);
        System.out.println("test:2 의 버킷 번호는 : " + data02);
        System.out.println("test:3 의 버킷 번호는 : " + data03);
        System.out.println("test:4 의 버킷 번호는 : " + data04);
        System.out.println("test:5 의 버킷 번호는 : " + data05);

    }

}
