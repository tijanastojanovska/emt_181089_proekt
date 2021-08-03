package com.example.shared_kernel.domain.events.orders;

import com.example.shared_kernel.domain.config.TopicHolder;
import com.example.shared_kernel.domain.events.DomainEvent;
import lombok.Getter;


@Getter
public class ReservationRemoved extends DomainEvent {

    private String ticketId;
    private int quantity;

    public ReservationRemoved(String topic) {
        super(TopicHolder.TOPIC_RESERVATION_REMOVED);
    }

    public ReservationRemoved(String topic, String ticketId, int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_REMOVED);
        this.ticketId = ticketId;
        this.quantity = quantity;
    }
}
