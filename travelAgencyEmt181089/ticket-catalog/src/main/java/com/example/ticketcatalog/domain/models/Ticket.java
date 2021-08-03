package com.example.ticketcatalog.domain.models;

import com.example.shared_kernel.domain.base.AbstractEntity;
import com.example.shared_kernel.domain.financial.Money;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Table(name="ticket")
@Getter
public class Ticket extends AbstractEntity<TicketId> {

    private String ticketName;

    private int soldTickets = 0;
//vrskata e many to one, edna destinacija moze da e pocetna za poveke tiketi, ne moze eden tiket da ima
//poveke pocetni destinacii
    @ManyToOne(fetch = FetchType.EAGER)
    private Destinacija pocetna;
    @ManyToOne( fetch = FetchType.EAGER)
    private Destinacija krajna;
    //Money price e value object, vo shared kerner, spodeleno megu dvata proekti order i ticket
    //attribute override za da ne nastane konflikt so iminjata
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Money price;

    public Ticket() {
        super(TicketId.randomId(TicketId.class));
    }
    public static Ticket build(String ticketName, Money price, int soldTickets, Destinacija pocetna, Destinacija krajna) {
        Ticket t = new Ticket();
        t.price = price;
        t.ticketName = ticketName;
        t.soldTickets = soldTickets;
        t.pocetna=pocetna;
        t.krajna=krajna;
        return t;
    }
//zgolemuva broj na prodadeni tiketi
    public void addSales(int qty) {
        this.soldTickets = this.soldTickets + qty;
    }
//namaluva broj na prodadeni tiketi
    public void removeSales(int qty) {
        this.soldTickets -= qty;
    }

}
