package com.example.ticketcatalog.config;

import com.example.shared_kernel.domain.financial.Currency;
import com.example.shared_kernel.domain.financial.Money;
import com.example.ticketcatalog.domain.models.Destinacija;
import com.example.ticketcatalog.domain.models.Ticket;
import com.example.ticketcatalog.repository.DestinacijaRepository;
import com.example.ticketcatalog.repository.TicketRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final TicketRepository ticketRepository;
    private final DestinacijaRepository destinacijaRepository;

    @PostConstruct
    public void initData() {
        Destinacija d1= Destinacija.build("Skopje","Makedonija");
        Destinacija d2= Destinacija.build("Belgrad","Srbija");
        Destinacija d3= Destinacija.build("Zagreb","Hrvatska");
        if (destinacijaRepository.findAll().isEmpty()) {
            destinacijaRepository.saveAll(Arrays.asList(d1,d2,d3));
        }
        Ticket t1 = Ticket.build("Skopje-Belgrad", Money.valueOf(Currency.MKD,1000), 2,d1,d2);
        Ticket t2 = Ticket.build("Belgrad-Skopje", Money.valueOf(Currency.MKD,1500), 3,d2,d1);
        Ticket t3 = Ticket.build("Skopje-Zagreb", Money.valueOf(Currency.MKD,1500), 3,d1,d3);
        if (ticketRepository.findAll().isEmpty()) {
            ticketRepository.saveAll(Arrays.asList(t1,t2,t3));
        }
    }
}
