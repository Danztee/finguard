package com.danztee.notification;

import com.danztee.clients.fraud.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class NotificationService {
    private NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {

        Notification notification = Notification.builder()
                .customerId(notificationRequest.customerId())
                .customerEmail(notificationRequest.customerEmail())
                .sender("Danztee")
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}
