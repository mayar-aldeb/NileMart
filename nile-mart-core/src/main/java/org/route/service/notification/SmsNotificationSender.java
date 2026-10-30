package org.route.service.notification;

import org.route.model.Customer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SmsNotificationSender implements NotificationSender {
    @Override
    public void sendNotification(Customer customer, String message) {
        System.out.println("[SMS] to "+customer.phone()+message);
    }
}
