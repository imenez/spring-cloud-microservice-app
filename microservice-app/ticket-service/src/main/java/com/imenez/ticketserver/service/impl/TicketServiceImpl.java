package com.imenez.ticketserver.service.impl;

import com.imenez.client.AccountServiceClient;
import com.imenez.client.contract.AccountDto;
import com.imenez.ticketserver.dto.TicketDto;
import com.imenez.ticketserver.model.PriorityType;
import com.imenez.ticketserver.model.Ticket;
import com.imenez.ticketserver.model.TicketStatus;
import com.imenez.ticketserver.model.es.TicketModel;
import com.imenez.ticketserver.repository.TicketRepository;
import com.imenez.ticketserver.repository.es.TicketElasticRepository;
import com.imenez.ticketserver.service.TicketNotificationService;
import com.imenez.ticketserver.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.apache.http.annotation.Contract;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketElasticRepository ticketElasticRepository;
    private final TicketRepository ticketRepository;
    private final TicketNotificationService ticketNotificationService;
    private final AccountServiceClient accountServiceClient;

    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        Ticket ticket = new Ticket();

        ResponseEntity<AccountDto> accountDtoResponseEntity = accountServiceClient.get(ticketDto.getAssignee());

        if(ticketDto.getDescription() == null)
            throw new IllegalArgumentException("Description cannot be null");

        //add to mysql ticket
        ticket.setDescription(ticketDto.getDescription());
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));
        ticket.setAssignee(accountDtoResponseEntity.getBody().getId());
        ticket = ticketRepository.save(ticket);

        //add to elastichsearch ticket
       /* TicketModel ticketModel = new TicketModel();
        ticketModel.setAssignee(accountDtoResponseEntity.getBody().getNameSurname());
        ticketModel.setDescription(ticket.getDescription());
        ticketModel.setNotes(ticket.getNotes());
        ticketModel.setId(ticket.getId());
        ticketModel.setPriorityType(ticket.getPriorityType().getLabel());
        ticketModel.setTicketStatus(ticket.getTicketStatus().getLabel());
        ticketModel.setTicketDate(ticket.getTicketDate());

        ticketElasticRepository.save(ticketModel);*/

       /* with @builder
       TicketModel ticketModel = TicketModel.builder()
                .description(ticket.getDescription())
                .notes(ticket.getNotes())
                .id(ticket.getId())
                .assignee(accountDtoResponseEntity.getBody().getNameSurname())
                .priorityType(ticket.getPriorityType().getLabel())
                .ticketStatus(ticket.getTicketStatus().getLabel())
                .ticketDate(ticket.getTicketDate()).build();*/



        Add2ElasticsearchKibana(ticketDto);
        ticketDto.setId(ticket.getId());

        //add to queue
        ticketNotificationService.sendToQueue(ticket);
        return ticketDto;
    }

    @Override
    @Transactional
    public TicketDto update(String id, TicketDto ticketDto) {

        Ticket ticket = new Ticket();

        ticket = ticketRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        ticket.setAssignee(ticketDto.getAssignee());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setNotes(ticketDto.getNotes());
        ticket.setDescription(ticketDto.getDescription());

        ticketRepository.save(ticket);


        Add2ElasticsearchKibana(ticketDto);


        return ticketDto;


    }



    @Override
    @Transactional
    public TicketDto getById(String ticketId) {

        TicketDto ticketDto = new TicketDto();
        Ticket ticket = new Ticket();

        ticket = ticketRepository.findById(ticketId).orElseThrow(IllegalArgumentException::new);
        ticketDto.setAssignee(ticket.getAssignee());
        ticketDto.setDescription(ticket.getDescription());
        ticketDto.setNotes(ticket.getNotes());
        ticketDto.setTicketDate(ticket.getTicketDate());
        ticketDto.setTicketStatus(ticket.getTicketStatus().toString());

        return ticketDto;

    }

    @Override
    public Page<TicketDto> getPageNation(Pageable pageable) {
        return null;
    }



    @Override
    @Transactional
    public String delete(String id) {


        ticketRepository.deleteById(id);

        return "Ticket Deleted";
    }



    private void Add2ElasticsearchKibana(TicketDto ticketDto) {


        //add to elastichsearch ticket
        ResponseEntity<AccountDto> accountDtoResponseEntity = accountServiceClient.get(ticketDto.getAssignee());
        TicketModel ticketModel = new TicketModel();
        Ticket ticket = new Ticket();

        ticketModel.setAssignee(accountDtoResponseEntity.getBody().getNameSurname());
        ticketModel.setDescription(ticket.getDescription());
        ticketModel.setNotes(ticket.getNotes());
        ticketModel.setId(ticket.getId());
        ticketModel.setPriorityType(ticket.getPriorityType().getLabel());
        ticketModel.setTicketStatus(ticket.getTicketStatus().getLabel());
        ticketModel.setTicketDate(ticket.getTicketDate());

        ticketElasticRepository.save(ticketModel);
    }

}
