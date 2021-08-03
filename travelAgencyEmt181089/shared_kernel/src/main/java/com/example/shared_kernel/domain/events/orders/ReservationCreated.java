package com.example.shared_kernel.domain.events.orders;

import com.example.shared_kernel.domain.config.TopicHolder;
import com.example.shared_kernel.domain.events.DomainEvent;
import lombok.Getter;


@Getter
public class ReservationCreated extends DomainEvent {

    private String ticketId;
    private int quantity;

    public ReservationCreated(String topic) {
        super(TopicHolder.TOPIC_RESERVATION_CREATED);
    }

    public ReservationCreated(String ticketId, int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_CREATED);
        this.ticketId = ticketId;
        this.quantity = quantity;
    }
}
