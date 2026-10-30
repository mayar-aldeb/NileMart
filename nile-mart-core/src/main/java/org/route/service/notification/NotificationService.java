package org.route.service.notification;

import org.route.model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private final List<NotificationSender> notifications = new ArrayList<>();



    public NotificationService(NotificationSender notificationSms,
                               @Qualifier("emailSender") NotificationSender notificationEmail) {
        notifications.add(notificationSms);
        notifications.add(notificationEmail);
    }

    public void sendNotifications(Customer customer, String message) {
        notifications.forEach(notification -> notification.sendNotification(customer, message));
    }
}
