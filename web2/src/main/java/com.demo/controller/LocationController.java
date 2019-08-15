package com.demo.controller;

import com.demo.domain.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/location")
class LocationController {

    @Autowired
    private UserService userService;gg

    @PostMapping(value = "/add")
    Mono<User> create(@RequestBody Mono<User> user) {
        return userService.add(user);
    }

    @GetMapping("/list")
    Flux<User> list() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    Mono<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/update/{id}")
    Mono<User> update(@PathVariable Long id,@RequestBody Mono<User> user){
        return userService.update(id,user);
    }

}
