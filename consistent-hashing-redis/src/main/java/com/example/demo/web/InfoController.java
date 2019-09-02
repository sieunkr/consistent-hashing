package com.example.demo.web;

import com.example.demo.provider.SimpleCacheTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class InfoController {

    private final SimpleCacheTemplate simpleCacheTemplate;

    @GetMapping("/buckets")
    public int getBucketByKey(@RequestParam(name = "key") String key){
        return simpleCacheTemplate.getBucket(key);
    }

    @GetMapping("/node")
    public String getNodeByKey(@RequestParam(name = "key") String key){
        return simpleCacheTemplate.getNode(key);
    }

    @GetMapping("/nodes")
    public List<String> getNodes(){
        return simpleCacheTemplate.getNodes();
    }
}