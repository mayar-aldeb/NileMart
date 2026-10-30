package org.route.service.notification;

import org.route.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface NotificationSender {
    void sendNotification(Customer customer, String message);
}
