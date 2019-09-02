package com.example.demo.provider;

import com.example.demo.core.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleCacheTemplateTest {
    @Autowired
    private SimpleCacheTemplate simpleCacheTemplate;

    @Test
    public void 대량_데이터_저장_성능_테스트(){
        long start = System.currentTimeMillis();

        for(int i = 0; i < 10000; i++){
            Person p = new Person();
            p.setName("test:" + i);
            p.setDescription("");
            simpleCacheTemplate.save(p).subscribe();
        }

        long end = System.currentTimeMillis();
        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
    }
}