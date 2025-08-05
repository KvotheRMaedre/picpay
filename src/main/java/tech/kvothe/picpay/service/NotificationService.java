package tech.kvothe.picpay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.kvothe.picpay.client.NotificationClient;
import tech.kvothe.picpay.entity.Transfer;

@Service
public class NotificationService {

    private final NotificationClient notificationClient;

    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer) {
        try {
            logger.info("Sending notification ...");
            var response = notificationClient.sendNotification(transfer);
            if (response.getStatusCode().isError()){
                logger.error("Error while sending notification, status code is not OK");
            }
        } catch (Exception e) {
            logger.error("Error while sending notification", e);
        }
    }
}
