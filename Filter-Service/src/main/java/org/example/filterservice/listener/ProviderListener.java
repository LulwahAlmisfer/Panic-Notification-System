package org.example.filterservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.filterservice.client.PanicNotificationClient;
import org.example.filterservice.client.models.Client;
import org.example.filterservice.models.Message;
import org.example.filterservice.models.Person;
import org.example.filterservice.publisher.FilterPublisher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProviderListener {

    private final FilterPublisher filterPublisher;

    private final PanicNotificationClient panicNotificationClient;

    @RabbitListener(queues = {"${rabbit.mq.pre-filter-queue}"})
    public void listener(Person person) {
        log.info("listener:: person from provider: {}", person);
        generateMessage(person);
    }

    private void generateMessage(Person person) {

        Set<Client> clients = panicNotificationClient.getClient(person.getId());

            for (Client client : clients) {

                Message message = Message.builder()
                        .personId(person.getId())
                        .personName(person.getName())
                        .personReason(person.getReason())
                        .clientId(client.getId())
                        .clientName(client.getName())
                        .clientUrl(client.getUrl())
                        .clientAuthHeader(client.getAuthorizationHeader())
                        .clientMethod(client.getMethod()).build();
                log.info("getClients:: generate message info for personId: {} and clientId {}", person.getId(), client.getId());

                filterPublisher.publishMessage(message);

            }

    }
}
