package com.example.ordermanagement.controllers;

import com.example.ordermanagement.domain.dtos.NewOrderDto;
import com.example.ordermanagement.domain.dtos.OrderDto;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.model.OrderStatus;
import com.example.ordermanagement.domain.valueobjects.Ticket;
import com.example.ordermanagement.domain.valueobjects.TicketId;
import com.example.ordermanagement.service.OrderService;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.ReservationForm;
import com.example.shared_kernel.domain.financial.Currency;
import com.example.shared_kernel.domain.financial.Money;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
//za da se pristapuva preku react
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
private final OrderService orderService;

    @PostMapping("/reserve")
    //vo save primam newOrderDto koe sodrzi samo ticket i quantity koi mi trebaat za da kreiram naracka
    public String save(@RequestBody NewOrderDto newOrderDto) {
//kreiram reservation form, kade gi stavam ticket koj go odbral korisnikot i quantity koe go vnel
        ReservationForm r1 = new ReservationForm();
        r1.setTicket(newOrderDto.getTicket());
        r1.setQuantity(newOrderDto.getQuantity());
 //kreiram orderform za naracka, kade items mi e rezerviraniot bilet, mkd e default currency i received mi e default status na narackata
        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(r1));
        orderForm.setStatus(OrderStatus.RECEIVED);
 //ja povikuvam placeOrder so orderForm koja ja kreirav pogore za da se  iskreira narackata
        OrderId newOrderId = orderService.placeOrder(orderForm);
        return newOrderId.getId();
    }

    @GetMapping
    public List<OrderDto> getAll() {
        //kreiram rest teplate koj ke go koristam za da go zemam biletot koj go odbral korisnikot
        RestTemplate restTemplate = new RestTemplate();
        //vo orders gi zemam site naracki
        List<Order> orders =  orderService.findAll();
        //mi treba novo OrderDto koe gi ima podatocite koi mi se potrebni za narackata
        List<OrderDto> dtos = new ArrayList<>();
        //gi izminuvam site naracki
        for(int i = 0; i< orders.size(); i++){
    //go naogam id na biletot od listata na rezervacii
String ticketId = orders.get(i).getReservationsList().stream().findFirst().get().getTicketId().getId();
//preku toa id, so restTemplate go naogam biletot
  Ticket ticket = restTemplate.getForObject("http://localhost:9090/api/tickets/"+ticketId, Ticket.class);
 OrderDto dto = new OrderDto();
 //gi postavuvam vrednostite vo dto
            dto.setCurrency(Currency.MKD);
            dto.setStatus(OrderStatus.RECEIVED);
            //da se prikazuva imeto na biletot
            dto.setTicketId(ticket.getTicketName());
            dto.setQuantity(orders.get(i).getReservationsList().stream().findFirst().get().getQuantity());
            dtos.add(dto);
        }
        //gi vrakam site orders, napraviv novo dto za da se vratat samo potrebnite raboti za prikaz vo tabelata
        return dtos;
    }
}
