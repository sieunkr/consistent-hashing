package com.example.demo.web;

import com.example.demo.core.Person;
import com.example.demo.core.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public Flux<Person> findAll(){
        return  personService.findAll();
    }

    @GetMapping("/{name}")
    public Mono<Person> findByName(@PathVariable(name = "name") String name){
        return personService.findByKey(name);
    }

    @PostMapping()
    public Mono<Void> save(@RequestBody Person person){
        return personService.save(person);
    }
}
