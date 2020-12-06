package com.imenez.ticketserver.model;

import lombok.Getter;

@Getter
public enum TicketStatus {
    OPEN("Acik"),
    IN_PROGRESS("Üzerinde Çalışılıyor"),
    RESOLVED("Çözüldü"),
    CLOSED("Kapandı");

    private String label;

    TicketStatus(String label) {
        this.label = label;
    }


}
