package com.example.ticketcatalog.repository;

import com.example.ticketcatalog.domain.models.Destinacija;
import com.example.ticketcatalog.domain.models.DestinacijaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinacijaRepository extends JpaRepository<Destinacija, DestinacijaId> {
}
