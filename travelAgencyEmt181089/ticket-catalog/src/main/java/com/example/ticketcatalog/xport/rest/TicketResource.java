package com.example.ticketcatalog.xport.rest;

import com.example.shared_kernel.domain.financial.Currency;
import com.example.shared_kernel.domain.financial.Money;
import com.example.ticketcatalog.domain.models.Ticket;
import com.example.ticketcatalog.domain.models.TicketId;
import com.example.ticketcatalog.services.TicketService;
import com.example.ticketcatalog.services.form.TicketForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketResource {

    private final TicketService ticketService;

    @GetMapping
    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable String id) {
        //pri povik, id se prima kako string, pa potoa pravam TicketId, za da moze da go najde
        TicketId ticketId=new TicketId(id);
        return this.ticketService.findById(ticketId)
                .map(ticket -> ResponseEntity.ok().body(ticket))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Ticket> save(@RequestBody TicketForm ticketForm) {
        //save ja povikuva create od service i ja predavam ticketform so podatoci koi gi primam od frontend
        return this.ticketService.createTicket(ticketForm)
                .map(ticket -> ResponseEntity.ok().body(ticket))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        //pri povik, id se prima kako string, pa potoa pravam TicketId
        TicketId ticketId=new TicketId(id);
        this.ticketService.deleteById(id);
        //go brisam biletot i proveruvam dali e izbrisan
        if(this.ticketService.findById(ticketId).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }


}
