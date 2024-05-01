package com.panic.dispatcherservice.listeners;

import com.panic.dispatcherservice.client.DispatcherClient;
import com.panic.dispatcherservice.config.MQConfig;
import com.panic.dispatcherservice.models.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MessageListener {

    private final DispatcherClient dispatchCallback;
    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(Message m) {
        dispatchCallback.dispatchCallback(m);
    }
}