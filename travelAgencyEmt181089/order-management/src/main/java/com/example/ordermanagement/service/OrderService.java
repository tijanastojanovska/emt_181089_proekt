package com.example.ordermanagement.service;

import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.model.ReservationId;
import com.example.ordermanagement.exceptions.OrderIdNotExistException;
import com.example.ordermanagement.exceptions.OrderItemIdNotExistException;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.ReservationForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderId placeOrder(OrderForm orderForm);

    List<Order> findAll();

    Optional<Order> findById(OrderId id);

    void addItem(OrderId orderId, ReservationForm reservationForm) throws OrderIdNotExistException;

    void deleteItem(OrderId orderId, ReservationId reservationId) throws OrderIdNotExistException, OrderItemIdNotExistException;



}
