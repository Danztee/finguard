package com.danztee.customer;

import com.danztee.amqp.RabbitMQMessageProducer;
import com.danztee.clients.fraud.FraudCheckResponse;
import com.danztee.clients.fraud.FraudClient;
import com.danztee.clients.fraud.NotificationClient;
import com.danztee.clients.fraud.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    //    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void register(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

//        check if email is valid

//        check if email is not taken

//        store customer in db
        customerRepository.saveAndFlush(customer);

//        check if fraudster
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD-SERVICE/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId());

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse == null) {
            throw new IllegalStateException("Fraud check response is null");
        }

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

//        send notification (now async)
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getEmail(),
                customer.getId(),
                String.format("Hi %s! welcome to the platform...", request.firstName()));


        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

//        notificationClient.sendNotification(
//                notificationRequest
//        );
    }
}
