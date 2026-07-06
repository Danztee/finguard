package com.danztee.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("notification-service")
public interface NotificationClient {

    @GetMapping("api/v1/notification")
    void sendNotification(NotificationRequest notificationRequest);
}
