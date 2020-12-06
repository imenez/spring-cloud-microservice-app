package com.imenez.ticketserver.service.impl;

import com.imenez.messaging.TicketNotification;
import com.imenez.ticketserver.model.Ticket;
import com.imenez.ticketserver.service.TicketNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableBinding(Source.class)
public class TicketNotificationServiceImpl implements TicketNotificationService {


    private final Source source;

    @Override
    public void sendToQueue(Ticket ticket){
        TicketNotification ticketNotification = new TicketNotification();
        ticketNotification.setTicketId(ticket.getId());
        ticketNotification.setTicketDescription(ticket.getDescription());
        ticketNotification.setAccountId(ticket.getAssignee());

        source.output().send(MessageBuilder.withPayload(ticketNotification).build());


    }
}
