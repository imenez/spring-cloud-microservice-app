package com.imenez.ticketserver.dto;

import lombok.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TicketDto {

    private String id;
    private String description;
    private String notes;
    private String assignee;
    private Date ticketDate;
    private String priorityType;
    private String ticketStatus;
}
