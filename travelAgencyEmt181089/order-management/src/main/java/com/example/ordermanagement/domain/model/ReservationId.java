package com.example.ordermanagement.domain.model;

import com.example.shared_kernel.domain.base.DomainObjectId;

public class ReservationId extends DomainObjectId {

    private ReservationId() {
        super(ReservationId.randomId(ReservationId.class).getId());
    }

    public ReservationId(String uuid) {
        super(uuid);
    }
}
