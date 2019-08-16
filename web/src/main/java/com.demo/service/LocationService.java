package com.demo.service;

import com.demo.domain.Location;
import com.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    /**
     * find Location by Id
     * @param id
     * @return
     */
    public Mono<Location> findById(Long id) {
        return Mono.justOrEmpty(locationRepository.findById(id));
    }

    /**
     * find List<Location>
     * @return
     */
    public Flux<Location> findAll() {
        return Flux.fromIterable(locationRepository.findAll());
    }

    /**
     * save Location
     * @param location
     * @return
     */
    @Transactional
    public Mono<Location> add(Mono<Location> location) {
        return Mono.justOrEmpty(locationRepository.save(location.block()));
    }

    /**
     * update Location
     * @return
     */
    @Transactional
    public Mono<Location> update(Long id,Mono<Location> location) {
        return Mono.justOrEmpty(locationRepository.saveAndFlush(location.block()));
    }

}
