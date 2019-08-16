package com.demo.controller;

import com.demo.domain.Location;
import com.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/location")
class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping(value = "/add")
    Mono<Location> create(@RequestBody Mono<Location> location) {
        return locationService.add(location);
    }

    @GetMapping("/list")
    Flux<Location> list() {
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    Mono<Location> findById(@PathVariable Long id) {
        return locationService.findById(id);
    }

    @PostMapping(value = "/update/{id}")
    Mono<Location> update(@PathVariable Long id,@RequestBody Mono<Location> location){
        return locationService.update(id,location);
    }

}
