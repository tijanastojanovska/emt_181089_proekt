package com.example.ordermanagement.service.forms;

import com.example.ordermanagement.domain.valueobjects.Ticket;
import lombok.Data;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ReservationForm {

    @NotNull
    private Ticket ticket;

    @Min(1)
    private int quantity = 1;
}
