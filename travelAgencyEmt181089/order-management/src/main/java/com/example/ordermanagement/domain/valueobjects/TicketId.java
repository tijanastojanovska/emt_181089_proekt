package com.example.ordermanagement.domain.valueobjects;

import com.example.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class TicketId extends DomainObjectId {

    public TicketId() {
        super(TicketId.randomId(TicketId.class).getId());
    }

    public TicketId(String uuid) {
        super(uuid);
    }


}
