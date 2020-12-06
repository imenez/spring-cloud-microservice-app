package com.imenez;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fb")
public class HystrixController {

    @GetMapping("/account")
    public String accountFallBack(){
        return "Account service is not available :( ";
    }

    @GetMapping("/ticket")
    public String ticketFallBack(){
        return "Ticket service is not available :( ";
    }

}
