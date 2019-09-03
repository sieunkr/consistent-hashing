package com.example.demo.config;

import com.example.demo.core.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class CacheConfig {

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory connectionFactoryNodeA() {
        return new LettuceConnectionFactory("192.168.19.136", 6379);
    }

    @Bean
    public ReactiveRedisConnectionFactory connectionFactoryNodeB() {
        return new LettuceConnectionFactory("192.168.19.137", 6379);
    }

    @Bean
    public ReactiveRedisConnectionFactory connectionFactoryNodeC() {
        return new LettuceConnectionFactory("192.168.19.138", 6379);
    }

    @Bean
    @Qualifier("reactiveRedisTemplateNodeA")
    public ReactiveRedisTemplate<String, Person> reactiveRedisTemplateNodeA(
            @Qualifier("connectionFactoryNodeA") ReactiveRedisConnectionFactory connectionFactoryNodeA) {

        return getStringPersonReactiveRedisTemplate(connectionFactoryNodeA);
    }

    @Bean
    @Qualifier("reactiveRedisTemplateNodeB")
    public ReactiveRedisTemplate<String, Person> reactiveRedisTemplateNodeB(
            @Qualifier("connectionFactoryNodeB") ReactiveRedisConnectionFactory connectionFactoryNodeB) {

        return getStringPersonReactiveRedisTemplate(connectionFactoryNodeB);
    }

    @Bean
    @Qualifier("reactiveRedisTemplateNodeC")
    public ReactiveRedisTemplate<String, Person> reactiveRedisTemplateNodeC(
            @Qualifier("connectionFactoryNodeC") ReactiveRedisConnectionFactory connectionFactoryNodeC) {

        return getStringPersonReactiveRedisTemplate(connectionFactoryNodeC);
    }

    private ReactiveRedisTemplate<String, Person> getStringPersonReactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactoryNode) {
        Jackson2JsonRedisSerializer<Person> serializer = new Jackson2JsonRedisSerializer<>(Person.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Person> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Person> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(
                connectionFactoryNode, context
        );
    }
}