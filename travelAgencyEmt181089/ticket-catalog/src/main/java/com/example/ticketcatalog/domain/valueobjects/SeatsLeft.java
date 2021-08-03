package com.example.ticketcatalog.domain.valueobjects;

import com.example.shared_kernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class SeatsLeft implements ValueObject {

    private final int seatsLeft;

    protected SeatsLeft() {
        this.seatsLeft = 50;
    }

}
