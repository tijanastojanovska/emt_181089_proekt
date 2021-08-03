package com.example.ordermanagement.domain.model;


import com.example.ordermanagement.domain.valueobjects.Ticket;
import com.example.shared_kernel.domain.base.AbstractEntity;
import com.example.shared_kernel.domain.financial.Currency;
import com.example.shared_kernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="orders")
//samo getter, ne smee da e anemicno, ne ni trebaat setters
@Getter
//aggregate root e Order
public class Order extends AbstractEntity<OrderId> {

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    //lista vo koja ke se dodavaat rezerviranite bileti
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Reservation> reservationsList = new HashSet<>();

    public Order() {
        super(OrderId.randomId(OrderId.class));
    }
    public Order(com.example.shared_kernel.domain.financial.Currency currency) {
        super(OrderId.randomId(OrderId.class));

        this.currency = currency;
    }
//presmetka na cena na naracka-cena na bilet*kolicina
    public Money total() {
        return reservationsList.stream().map(Reservation::subtotal).reduce(new Money(currency, 0), Money::add);
    }
    //ovoj Ticket e value object, bidejki ne smee da se koristi Ticket od ticket catalog
    public Reservation addItem(@NonNull Ticket ticket, int qty) {
        Objects.requireNonNull(ticket,"ticket must not be null");
        var reservation  = new Reservation(ticket.getId(),ticket.getPrice(),qty);
        reservationsList.add(reservation);
        return reservation;
    }

    public void removeItem(@NonNull ReservationId reservationId) {
        Objects.requireNonNull(reservationId,"Reservation must not be null");
        reservationsList.removeIf(v->v.getId().equals(reservationId));
    }
}