package com.example.ordermanagement.domain.model;


import com.example.ordermanagement.domain.valueobjects.TicketId;
import com.example.shared_kernel.domain.base.AbstractEntity;
import com.example.shared_kernel.domain.base.DomainObjectId;
import com.example.shared_kernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
@Getter
//samo getter, ne smee da ima setter
public class Reservation extends AbstractEntity<ReservationId> {

    private Money reservationPrice;
//kolku bileti saka da rezervira
    @Column(name = "qty", nullable = false)
    private int quantity;
//ova TicketId e value object
    @AttributeOverride(name = "id", column = @Column(name = "ticket_id", nullable = false))
    private TicketId ticketId;

    public Reservation() {
        super(DomainObjectId.randomId(ReservationId.class));
    }

    public Reservation(@NonNull TicketId ticketId, @NonNull Money reservationPrice, int qty) {
        super(DomainObjectId.randomId(ReservationId.class));
        this.ticketId = ticketId;
        this.reservationPrice = reservationPrice;
        this.quantity = qty;
    }

    public Money subtotal() {
        return reservationPrice.multiply(quantity);
    }
}
