package com.example.ordermanagement.domain.dtos;

import com.example.ordermanagement.domain.model.OrderStatus;
import com.example.ordermanagement.service.forms.ReservationForm;
import com.example.shared_kernel.domain.financial.Currency;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Data
//moze da e oznaceno so @Data, to est da ima getters i setters
public class OrderDto {
//potrebno za da kreiram za da dodadam order
    private Currency currency;
    private OrderStatus status;
   private String ticketId;
    private int quantity;
}
