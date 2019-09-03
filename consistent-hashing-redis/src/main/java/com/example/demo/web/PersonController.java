package com.example.demo.web;

import com.example.demo.core.Person;
import com.example.demo.core.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    //단 하나의 데이터만 리턴한다는 가정, Mono
    @GetMapping("/{name}")
    public Mono<Person> findByName(@PathVariable(name = "name") String name){
        return personService.findByKey(name);
    }

    @PostMapping()
    public Mono<Void> save(@RequestBody Person person){
        return personService.save(person);
    }
}