package com.example.ticketcatalog.xport.events;

import com.example.shared_kernel.domain.config.TopicHolder;
import com.example.shared_kernel.domain.events.DomainEvent;
import com.example.shared_kernel.domain.events.orders.ReservationCreated;
import com.example.shared_kernel.domain.events.orders.ReservationRemoved;
import com.example.ticketcatalog.domain.models.TicketId;
import com.example.ticketcatalog.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class TicketEventListener {

    private final TicketService ticketService;
//koga se kreira rezervacija, kazuvam deka groupId e ticketCatalog
    @KafkaListener(topics= TopicHolder.TOPIC_RESERVATION_CREATED, groupId = "ticketCatalog")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            //event za kreirana rezervacija
            ReservationCreated event = DomainEvent.fromJson(jsonMessage,ReservationCreated.class);
            ticketService.orderItemCreated(TicketId.of(event.getTicketId()), event.getQuantity());
        } catch (Exception e){

        }

    }


    @KafkaListener(topics= TopicHolder.TOPIC_RESERVATION_REMOVED, groupId = "ticketCatalog")
    public void consumeOrderItemRemovedEvent(String jsonMessage) {
        try {
            //event za otkazana rezervacija
            ReservationRemoved event = DomainEvent.fromJson(jsonMessage,ReservationRemoved.class);
            ticketService.orderItemRemoved(TicketId.of(event.getTicketId()), event.getQuantity());
        } catch (Exception e){

        }

    }
}