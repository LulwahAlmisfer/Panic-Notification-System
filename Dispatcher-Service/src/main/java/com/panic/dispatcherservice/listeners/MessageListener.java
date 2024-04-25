package com.panic.dispatcherservice.listeners;

import com.panic.dispatcherservice.Config.MQConfig;
import com.panic.dispatcherservice.models.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(Person p) {
        System.out.println(p);
         //add callback logic
    }

}