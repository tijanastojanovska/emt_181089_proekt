package com.example.ordermanagement.domain.valueobjects;

import com.example.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class DestinacijaId extends DomainObjectId {

    public DestinacijaId() {
        super(DestinacijaId.randomId(DestinacijaId.class).getId());
    }

    public DestinacijaId(String uuid) {
        super(uuid);
    }


}
