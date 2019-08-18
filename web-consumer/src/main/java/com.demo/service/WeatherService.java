package com.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {
    private final WebClient webClient;

    public WeatherService() {
        this.webClient = WebClient.builder().baseUrl("https://samples.openweathermap.org/data/2.5/forecast").build();
    }

    public Mono<String> pullWeather(String cityNameCountryCode) {
        return this.webClient.get().uri("?q={cityNameCountryCode}&appid=b6907d289e10d714a6e88b30761fae22", cityNameCountryCode)
                .retrieve().bodyToMono(String.class);
    }



}
