package com.imenez.ticketserver.repository.es;

import com.imenez.ticketserver.model.es.TicketModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel, String> {
}
