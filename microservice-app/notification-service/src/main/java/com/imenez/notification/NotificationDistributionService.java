package com.imenez.notification;


import com.imenez.messaging.TicketNotification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class NotificationDistributionService {

    private static final Logger logger = LogManager.getLogger(NotificationDistributionService.class);

    @StreamListener(Sink.INPUT)
    public void onNotification(TicketNotification ticketNotification){

        System.out.println("###########################################################");
        System.out.println("Notification received, sending to user..");
        System.out.println(ticketNotification.toString());


        //logging
        logger.info(ticketNotification.getAccountId());
        logger.info(ticketNotification.getTicketId());
        logger.info(ticketNotification.getTicketDescription());


    }
}
