package com.demo.controller;

import com.demo.kafka.Producer;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    public KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam String countryCode, @RequestParam String cityName){
        String uniqueID = UUID.randomUUID().toString();
        this.producer.sendMessage(uniqueID+"|"+cityName+ ","+countryCode);
        return JSONObject.quote(uniqueID);


    }
}