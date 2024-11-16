package org.example.filterservice.publisher;

import lombok.extern.slf4j.Slf4j;
import org.example.filterservice.models.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FilterPublisher {


    public String exchange = "exchange";

    @Value("${custom.rabbitmq.post-filter-routing-key}")
    public String postFilterRoutingKey;

    @Autowired
    private RabbitTemplate template;


    public void publishMessage(Message message) {
        try {
            template.convertAndSend(exchange, postFilterRoutingKey, message);
            log.info("publishMessage:: message published successfully");
        } catch (Exception e) {

        }
    }

}
