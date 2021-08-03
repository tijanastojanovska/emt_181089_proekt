package com.example.ticketcatalog.xport.rest;


import com.example.ticketcatalog.domain.models.Destinacija;
import com.example.ticketcatalog.domain.models.Ticket;
import com.example.ticketcatalog.services.DestinacijaService;
import com.example.ticketcatalog.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/destinacii")
@AllArgsConstructor
public class DestinacijaController {

    private final DestinacijaService destinacijaService;
//kontroler za destinacija, za da mozam da gi listam destiaciite
    @GetMapping
    public List<Destinacija> getAll() {
        return destinacijaService.listDestinacii();
    }


}