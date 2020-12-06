package com.imenez.ticketserver.service;

import com.imenez.ticketserver.model.Ticket;

public interface TicketNotificationService {

    void sendToQueue(Ticket ticket);
}
