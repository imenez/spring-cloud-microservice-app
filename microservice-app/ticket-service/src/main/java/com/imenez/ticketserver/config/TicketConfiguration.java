package com.imenez.ticketserver.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.imenez")
@EnableElasticsearchRepositories
@ComponentScan("com.imenez")
public class TicketConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
