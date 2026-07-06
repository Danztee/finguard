package com.danztee.clients.fraud;

public record NotificationRequest(String customerEmail, Integer customerId, String message) {
}
