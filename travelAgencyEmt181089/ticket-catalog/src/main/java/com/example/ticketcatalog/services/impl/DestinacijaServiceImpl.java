package com.example.ticketcatalog.services.impl;

import com.example.ticketcatalog.domain.models.Destinacija;
import com.example.ticketcatalog.repository.DestinacijaRepository;
import com.example.ticketcatalog.services.DestinacijaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class DestinacijaServiceImpl implements DestinacijaService {

private final DestinacijaRepository destinacijaRepository;
//potreben mi e servis za da mozam da gi izlistam destinaciite
    @Override
    public List<Destinacija> listDestinacii() {
        return destinacijaRepository.findAll();
    }

}
