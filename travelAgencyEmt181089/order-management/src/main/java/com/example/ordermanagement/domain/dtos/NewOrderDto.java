package com.example.ordermanagement.domain.dtos;

import com.example.ordermanagement.domain.valueobjects.Ticket;
import lombok.Data;

@Data
public class NewOrderDto {
    //potrebno mi e koga kreiram naracka, da imam ticket quantity koe go vnesuva korisnikot
    private Ticket ticket;
    private int quantity;
}
