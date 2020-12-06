package com.imenez.ticketserver.service;

import com.imenez.ticketserver.dto.TicketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {

    TicketDto save(TicketDto ticketDto);

    TicketDto update(String id, TicketDto ticketDto);

    TicketDto getById(String ticketId);

    Page<TicketDto> getPageNation(Pageable pageable);

    String delete(String id);
}
