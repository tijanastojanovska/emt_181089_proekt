package com.example.ticketcatalog.repository;

import com.example.ticketcatalog.domain.models.Ticket;
import com.example.ticketcatalog.domain.models.TicketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, TicketId> {
}
