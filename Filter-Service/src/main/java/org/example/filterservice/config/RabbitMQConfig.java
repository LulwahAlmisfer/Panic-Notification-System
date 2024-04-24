package org.example.filterservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbit.mq.pre-filter-queue}")
    public String preFilterQueue;

    @Value("${rabbit.mq.pre-filter-routing-key}")
    public String preFilterRoutingKey;

    @Value("${rabbit.mq.post-filter-queue}")
    public String postFilterQueue;

    @Value("${rabbit.mq.post-filter-routing-key}")
    public String postFilterRoutingKey;

    @Value("${rabbit.mq.exchange}")
    public String exchange;

    @Bean
    public Queue preFilterQueue() {
        return new Queue(preFilterQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding bindingPreFilterQueue() {
        return BindingBuilder
                .bind(preFilterQueue())
                .to(exchange())
                .with(preFilterRoutingKey);
    }

    @Bean
    public Queue postFilterQueue() {
        return new Queue(postFilterQueue);
    }

    @Bean
    public Binding bindingPostFilterQueue() {
        return BindingBuilder
                .bind(postFilterQueue())
                .to(exchange())
                .with(postFilterRoutingKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

}