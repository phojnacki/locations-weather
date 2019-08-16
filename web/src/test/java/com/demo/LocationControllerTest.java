package com.demo.controller;

import com.demo.domain.Location;
import com.demo.service.LocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationControllerTest {

    @Autowired
    private LocationService locationService;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testCreate() {
        Location location = new Location();
        location.setName("abcd");
        this.webClient.post().uri("/location/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(location),Location.class).exchange()
                .expectStatus().isOk();
//                .expectBody(Location.class).isEqualTo(location);
//                .consumeWith(result -> assertEquals("Response result","abcd", result.getResponseBody().getName()));
    }

    @Test
    public void testFindById() {
        Location location = new Location();
        location.setName("findById");
        Location newLocation = locationService.add(Mono.just(location)).block();
        this.webClient.get().uri("/location/{id}",newLocation.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Location.class).isEqualTo(newLocation);
    }

    @Test
    public void testList() {

    }

    @Test
    public void testUpdate(){

    }

}
