package com.danztee.notification.rabbitmq;

import com.danztee.clients.fraud.NotificationRequest;
import com.danztee.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public  void consumer(NotificationRequest notificationRequest) {
        log.info("Received Notification Request: {}", notificationRequest);

        notificationService.send(notificationRequest);
    }
}
