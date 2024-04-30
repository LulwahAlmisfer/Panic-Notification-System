package org.example.filterservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.filterservice.client.PanicNotificationClient;
import org.example.filterservice.client.models.Client;
import org.example.filterservice.client.models.Notification;
import org.example.filterservice.models.Message;
import org.example.filterservice.models.Person;
import org.example.filterservice.publisher.FilterPublisher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Objects;
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

        if (clients.isEmpty()) {
            log.info("No clients found for personId: {}", person.getId());
            return;
        }

        for (Client client : clients) {
            Set<Notification> subscribedNotifications  =
                    panicNotificationClient.getNotifications(client.getId());

            for ( Notification s: subscribedNotifications) {
                if (Objects.equals(s.getId(), person.getReason())){
                    Message message = Message.builder()
                            .personId(person.getId())
                            .personName(person.getName())
                            .personReason(person.getReason())
                            .clientId(client.getId())
                            .clientName(client.getName())
                            .clientUrl(client.getUrl())
                            .clientAuthHeader(client.getAuthorizationHeader())
                            .clientMethod(client.getMethod()).build();
                    log.info("generateMessage:: generate message info for personId: {} and clientId: {}", person.getId(), client.getId());
                    filterPublisher.publishMessage(message);
                }
            }
        }
    }
}
