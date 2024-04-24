package com.panic.receiverservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client")
public class Controller {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish-list")
    public ResponseEntity publishList(@RequestBody ArrayList<Person> persons) {

        persons.forEach((s) ->
                template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, s));

        return ResponseEntity.ok().body("sent to RabbitMQ");
    }

}
