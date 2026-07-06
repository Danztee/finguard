package com.danztee.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notification-service")
public interface NotificationClient {

    @PostMapping("/api/v1/notification")
    void sendNotification(NotificationRequest notificationRequest);
}
