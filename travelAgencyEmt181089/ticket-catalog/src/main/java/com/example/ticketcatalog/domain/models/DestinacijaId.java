package com.example.ticketcatalog.domain.models;

import com.example.shared_kernel.domain.base.DomainObjectId;
import lombok.NonNull;

public class DestinacijaId extends DomainObjectId {
    //id go pretstavuvam kako DestinacijaId koe nasleduva od DomainObjectId soglasno so ddd principite
    private DestinacijaId() {
        super(DestinacijaId.randomId(DestinacijaId.class).getId());
    }

    public DestinacijaId(@NonNull String uuid) {
        super(uuid);
    }

    public static DestinacijaId of(String uuid) {
        DestinacijaId d = new DestinacijaId(uuid);
        return d;
    }
}
