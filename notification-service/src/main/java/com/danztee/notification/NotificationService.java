package com.danztee;

import com.danztee.clients.fraud.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {
    private NotificationRepository notificationRepository;

    void send(NotificationRequest notificationRequest) {

        notificationRepository.save(
                Notification.builder().build()
        )
    }
}
