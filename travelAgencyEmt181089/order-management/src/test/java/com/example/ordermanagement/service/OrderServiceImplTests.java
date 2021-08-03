package com.example.ordermanagement.service;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.valueobjects.Destinacija;
import com.example.ordermanagement.domain.valueobjects.DestinacijaId;
import com.example.ordermanagement.domain.valueobjects.Ticket;
import com.example.ordermanagement.domain.valueobjects.TicketId;
import com.example.ordermanagement.exceptions.OrderIdNotExistException;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.ReservationForm;
import com.example.ordermanagement.xport.client.TicketClient;
import com.example.shared_kernel.domain.financial.Currency;
import com.example.shared_kernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
//testovi za kreiranje naracka i proverka na povicite
@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketClient ticketClient;



    private static Ticket newTicket(String name, Money price, Destinacija pocetna, Destinacija krajna) {
        Ticket t = new Ticket(TicketId.randomId(TicketId.class), name, price, 0,pocetna,krajna);
        return t;
    }
    private static Destinacija newDestination(String name, String country) {
        Destinacija d=new Destinacija(DestinacijaId.randomId(DestinacijaId.class),name,country);
        return d;
    }

    @Test
    public void
    testPlaceOrder() {

        ReservationForm r1 = new ReservationForm();
        Destinacija d1=newDestination("Skopje", "Makedonija");
        Destinacija d2=newDestination("Belgrad", "Srbija");
        r1.setTicket(newTicket("Prv",Money.valueOf(Currency.MKD,1500),d1,d2));
        r1.setQuantity(1);

        ReservationForm r2 = new ReservationForm();
        r2.setTicket(newTicket("Vtor",Money.valueOf(Currency.MKD,1000),d2,d1));
        r2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(r1,r2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);
        Assertions.assertEquals(newOrder.total(),Money.valueOf(Currency.MKD,3500));

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Ticket> tickets = ticketClient.findAll();
        Ticket t1 = tickets.get(0);
        Ticket t2 = tickets.get(1);

        ReservationForm r1 = new ReservationForm();
        r1.setTicket(t1);
        r1.setQuantity(1);

        ReservationForm r2 = new ReservationForm();
        r2.setTicket(t2);
        r2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(r1,r2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = t1.getPrice().multiply(r1.getQuantity()).add(t2.getPrice().multiply(r2.getQuantity()));
        Assertions.assertEquals(newOrder.total(),outMoney);
    }


}
