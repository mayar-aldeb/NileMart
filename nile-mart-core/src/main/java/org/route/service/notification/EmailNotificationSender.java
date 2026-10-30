package org.route.service.notification;

import org.route.model.Customer;
import org.springframework.stereotype.Service;

@Service("emailSender")
public class EmailNotificationSender implements NotificationSender {
    @Override
    public void sendNotification(Customer customer, String message) {
        if(customer.email()==null)
            return;
        System.out.println("[EMAIL] to "+customer.email()+message);
    }
}
