package com.danztee;

import com.danztee.clients.fraud.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notificaitons")
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping
    public void sendNotification(NotificationRequest notificationRequest) {
        log.info("Sending notification {}", notificationRequest);
        notificationService.send(notificationRequest);
    }

}
