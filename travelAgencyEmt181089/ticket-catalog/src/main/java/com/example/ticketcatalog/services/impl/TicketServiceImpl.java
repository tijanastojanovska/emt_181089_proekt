package com.example.ticketcatalog.services.impl;

import com.example.shared_kernel.domain.financial.Currency;
import com.example.shared_kernel.domain.financial.Money;
import com.example.ticketcatalog.domain.exceptions.TicketNotFoundException;
import com.example.ticketcatalog.domain.models.Destinacija;
import com.example.ticketcatalog.domain.models.DestinacijaId;
import com.example.ticketcatalog.domain.models.Ticket;
import com.example.ticketcatalog.domain.models.TicketId;
import com.example.ticketcatalog.repository.DestinacijaRepository;
import com.example.ticketcatalog.repository.TicketRepository;
import com.example.ticketcatalog.services.TicketService;
import com.example.ticketcatalog.services.form.TicketForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final DestinacijaRepository destinacijaRepository;

    @Override
    public Optional<Ticket> findById(TicketId id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Optional<Ticket> createTicket(TicketForm form) {
        //prima ticketform, od kade zemam pocetna i krajna destinacija
        DestinacijaId destinacijaIdPocetna = new DestinacijaId(form.getPocetnaId());
        Destinacija pocetnaD = destinacijaRepository.findById(destinacijaIdPocetna).orElseThrow(TicketNotFoundException::new);
        DestinacijaId destinacijaIdKrajna = new DestinacijaId(form.getKrajnaId());
        Destinacija krajnaD = destinacijaRepository.findById(destinacijaIdKrajna).orElseThrow(TicketNotFoundException::new);
 //kreiram ticket so podatocite koi gi zemam od ticketform za name, price, soldTickets i pocetna i krajna destinacija
        Ticket p = Ticket.build(form.getTicketName(),Money.valueOf(Currency.MKD,(double)form.getPriceNumber()),form.getSoldTickets(), pocetnaD, krajnaD);
        //go zacuvuvam noviot ticket
        ticketRepository.save(p);
        return Optional.of(p);
    }

    @Override
    public void deleteById(String id) {
        TicketId ticketId=new TicketId(id);
        this.ticketRepository.deleteById(ticketId);
    }
    @Override
    public Ticket orderItemCreated(TicketId TicketId, int quantity) {
        //kreira rezervacija za ticket koj go naogam preku id
        Ticket p = ticketRepository.findById(TicketId).orElseThrow(TicketNotFoundException::new);
        //dodava kolicina na rezervirani bileti
        p.addSales(quantity);
        ticketRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public Ticket orderItemRemoved(TicketId TicketId, int quantity) {
        Ticket p = ticketRepository.findById(TicketId).orElseThrow(TicketNotFoundException::new);
        p.removeSales(quantity);
        ticketRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }
}
