package com.example.ordermanagement.infra;

import com.example.shared_kernel.domain.events.DomainEvent;
import com.example.shared_kernel.domain.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {
//koristam kafka kako message broker, potrebno e da vo cmd da gi pocnam zookeeper i kafka za da rabotat proektite
    private final KafkaTemplate<String,String> kafkaTemplate;
//pravi publish na nastanot, koristime toJson za da gi koristi pod.
    @Override
    public void publish(DomainEvent event) {
        this.kafkaTemplate.send(event.topic(),event.toJson());
    }
}
