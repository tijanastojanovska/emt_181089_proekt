package com.example.ticketcatalog.services.form;

import com.example.shared_kernel.domain.financial.Money;
import com.example.ticketcatalog.domain.models.Destinacija;
import lombok.Data;


@Data
//ima i getters i setters, ovaa klasa gi ima site potrebni atributi koi gi koristam pri kreiranje ticket
public class TicketForm {
    private String ticketName;
    private Money price;
    private Double priceNumber;
    private int soldTickets;
    Destinacija pocetna;
    String pocetnaId;
    Destinacija krajna;
    String krajnaId;
}
