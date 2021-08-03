package com.example.ordermanagement.domain.valueobjects;

import com.example.shared_kernel.domain.base.ValueObject;
import com.example.shared_kernel.domain.financial.Currency;
import com.example.shared_kernel.domain.financial.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.print.attribute.standard.Destination;

@Getter
public class Ticket implements ValueObject {
    //potrebno e da go imame kako valueObject bidejki ne smee da se pristapi i koristi od ticketCatalog
    private final TicketId id;
    private final String ticketName;
    private final Money price;
    private final int soldTickets;
    private final Destinacija pocetna;
    private final Destinacija krajna;

    private Ticket() {
        this.id= TicketId.randomId(TicketId.class);
        this.ticketName= "";
        this.price = Money.valueOf(Currency.MKD,0);
        this.soldTickets = 0;
        this.pocetna=getPocetna();
        this.krajna=getKrajna();
    }
//potrebno za publikuvanjeto nastani da gi ima kako jsonProperties
    @JsonCreator
    public Ticket(@JsonProperty("id") TicketId id,
                  @JsonProperty("TicketName") String ticketName,
                  @JsonProperty("price") Money price,
                  @JsonProperty("soldTickets") int soldTickets,
                  @JsonProperty("pocetna") Destinacija pocetna,
                  @JsonProperty("krajna") Destinacija krajna) {
        this.id = id;
        this.ticketName = ticketName;
        this.price = price;
        this.soldTickets = soldTickets;
        this.pocetna=pocetna;
        this.krajna=krajna;
    }
}
