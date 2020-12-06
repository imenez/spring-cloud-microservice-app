package com.imenez.ticketserver.api;

import com.imenez.ticketserver.dto.TicketDto;
import com.imenez.ticketserver.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketApi {

    private final TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getById(@PathVariable String id){

        return ResponseEntity.ok(ticketService.getById(id));

    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto){
        return ResponseEntity.ok(ticketService.save(ticketDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable String id,
                                                  @RequestBody TicketDto ticketDto){

        return ResponseEntity.ok(ticketService.update(id, ticketDto));

    }

    @GetMapping
    public ResponseEntity<Page<TicketDto>> getAll(Pageable pageable){

        return ResponseEntity.ok(ticketService.getPageNation(pageable));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable String id){

        return ResponseEntity.ok(ticketService.delete(id));

    }
}
