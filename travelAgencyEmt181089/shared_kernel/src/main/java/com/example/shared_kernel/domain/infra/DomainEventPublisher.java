package com.example.shared_kernel.domain.infra;


import com.example.shared_kernel.domain.events.DomainEvent;

public interface DomainEventPublisher {
    //interface za publish
    void publish(DomainEvent event);
}
