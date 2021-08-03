package com.example.ordermanagement.service.impl;

import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.model.ReservationId;
import com.example.ordermanagement.exceptions.OrderIdNotExistException;
import com.example.ordermanagement.exceptions.OrderItemIdNotExistException;
import com.example.ordermanagement.repository.OrderRepository;
import com.example.ordermanagement.service.OrderService;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.ReservationForm;
import com.example.shared_kernel.domain.events.orders.ReservationCreated;
import com.example.shared_kernel.domain.events.orders.ReservationRemoved;
import com.example.shared_kernel.domain.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.websocket.server.ServerEndpoint;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;
    // za da se izvrsi narackata se prima formata
    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
//proveruvam dali ima nekoe narusuvanje na pravilata
            Set<ConstraintViolation<OrderForm>> constraintViolations = validator.validate(orderForm);

        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
       }
        //ako e se vo red, zacuvuvam naracka
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getReservationsList().forEach(item->domainEventPublisher.publish(new ReservationCreated(item.getTicketId().getId(),item.getQuantity())));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, ReservationForm reservationForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addItem(reservationForm.getTicket(),reservationForm.getQuantity());
        orderRepository.saveAndFlush(order);
        //publikivam event deka e kreirana rezervacija, koristam domainEventPublisher od shared kernel
        domainEventPublisher.publish(new ReservationCreated(reservationForm.getTicket().getId().getId(),reservationForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId orderId, ReservationId reservationId) throws OrderIdNotExistException, OrderItemIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeItem(reservationId);
        orderRepository.saveAndFlush(order); }

    //pretvara od orderform vo order
    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(orderForm.getCurrency());
        //gi dodavam vo order so addItem
        orderForm.getItems().forEach(item->order.addItem(item.getTicket(),item.getQuantity()));
        return order;
    }
}
