package com.demo;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

//	@Bean
//	public ProducerFactory<String, Location> greetingProducerFactory() {
//		Map<String, Object> configProps = new HashMap<>();
//		configProps.put(
//				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//				JsonSerializer.class);
//		return new DefaultKafkaProducerFactory<>(configProps);
//	}
//
//	@Bean
//	public KafkaTemplate<String, Location> greetingKafkaTemplate() {
//		return new KafkaTemplate<>(greetingProducerFactory());
//	}

}
