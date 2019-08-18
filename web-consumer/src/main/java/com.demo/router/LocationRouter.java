package com.demo.router;

import com.demo.domain.Location;
import com.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;

import reactor.core.publisher.Mono;

@Component
public class LocationRouter {

    @Autowired
    private LocationService locationService;

    public RouterFunction<ServerResponse> routes() {
        return nest(path("/location"),
                route(GET("/list").and(accept(APPLICATION_JSON)), this::listAll)
                        .andRoute(GET("/{requestId}").and(accept(APPLICATION_JSON)), this::findByRequestId)
                        .andRoute(POST("/add").and(accept(APPLICATION_JSON)), this::add)
                        .andRoute(POST("/update/{id}").and(accept(APPLICATION_JSON)), this::update));
    }

    private Mono<ServerResponse> findByRequestId(ServerRequest req){
        String requestId = req.pathVariable("requestId");
//        Mono<Location> locationMono = locationService.findByRequestId(requestId);
//        locationMono.sub
        return ok().body(locationService.findByRequestId(requestId), Location.class);
    }

    private Mono<ServerResponse> listAll(ServerRequest req) {
        return ok().body(locationService.findAll(), Location.class);
    }

    private Mono<ServerResponse> add(ServerRequest req) {
        return ok().body(locationService.add(req.bodyToMono(Location.class)), Location.class);
    }

    private Mono<ServerResponse> update(ServerRequest req) {
        Long id = Long.valueOf(req.pathVariable("id"));
        return ok().body(locationService.update(id,req.bodyToMono(Location.class)), Location.class);
    }

}
