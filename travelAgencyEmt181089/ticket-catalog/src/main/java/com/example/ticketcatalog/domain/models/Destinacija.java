package com.example.ticketcatalog.domain.models;

import com.example.shared_kernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="destination")
@Getter
//samo getter, ne smee da ima setter za postavuvanje na vrednosti
public class Destinacija extends AbstractEntity<DestinacijaId> {
    public String name;
    public String county;
    public Destinacija() {
        super(DestinacijaId.randomId(DestinacijaId.class));
    }
    public static Destinacija build(String name, String county) {
        Destinacija d = new Destinacija();
        d.name=name;
        d.county=county;
        return d;
    }
}
