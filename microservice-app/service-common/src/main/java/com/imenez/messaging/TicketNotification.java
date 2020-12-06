package com.imenez.messaging;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketNotification implements Serializable {

    private String ticketId;
    private String accountId;
    private String ticketDescription;

}
