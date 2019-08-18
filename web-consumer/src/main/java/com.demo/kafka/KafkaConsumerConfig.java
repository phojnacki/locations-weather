package com.demo.kafka;

import com.demo.domain.Location;
import com.demo.service.AverageCalculator;
import com.demo.service.LocationService;
import com.demo.service.WeatherService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private static final String GROUP_ID = "group-one";

    @Autowired
    WeatherService weatherService;

    @Autowired
    LocationService locationService;

    @Autowired
    AverageCalculator averageCalculator;

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                GROUP_ID);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = "locations-weather", groupId = GROUP_ID)
    public void listen(String message) {
        System.out.println("Received Messasge : " + message);
        weatherService.pullWeather(message).subscribe(
                value -> {
                    String weatherResult = String.valueOf(value);
                    JSONObject weatherResultJSON = new JSONObject(weatherResult);
                    Location location = averageCalculator.locationWithAveragesForWeather(weatherResultJSON, message);
                    locationService.add(Mono.just(location));
                    },
                error -> error.printStackTrace(),
                () -> System.out.println("completed without a value"));

    }
}