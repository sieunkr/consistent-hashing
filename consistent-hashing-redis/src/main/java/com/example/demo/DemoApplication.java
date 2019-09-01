package com.example.demo;

import com.example.demo.core.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.ReactiveRedisOperations;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

	/*
	@Qualifier("reactiveRedisTemplateNodeA")
	private final ReactiveRedisOperations<String, Person> reactiveRedisTemplateNodeA;
	 */

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		Person n = new Person();
		n.setDescription("dd");
		n.setName("eddy");


		reactiveRedisTemplateNodeA.opsForValue().set("eddy", n).subscribe();
		*/

	}
}
