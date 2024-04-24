package org.example.filterservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.filterservice.models.Person;
import org.example.filterservice.publisher.FilterPublisher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProviderListener {

    private final FilterPublisher filterPublisher;

    @RabbitListener(queues = {"${rabbit.mq.pre-filter-queue}"})
    public void listener(Person person) {
        log.info("listener:: person before: {}", person);
        person.setTest("after");
        log.info("listener:: person after: {}", person);
        filterPublisher.publishMessage(person);
    }

}
