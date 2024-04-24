package org.example.filterservice.publisher;

import lombok.extern.slf4j.Slf4j;
import org.example.filterservice.models.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FilterPublisher {

    @Value("${rabbit.mq.exchange}")
    public String exchange;

    @Value("${rabbit.mq.post-filter-routing-key}")
    public String postFilterRoutingKey;

    @Autowired
    private RabbitTemplate template;


    public void publishMessage(Person person) {
        try {
            template.convertAndSend(exchange, postFilterRoutingKey, person);
            log.info("publishMessage:: message published successfully");
        } catch (Exception e) {

        }
    }

}
