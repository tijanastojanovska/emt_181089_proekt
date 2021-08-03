package com.example.ordermanagement.domain.valueobjects;

import com.example.shared_kernel.domain.base.ValueObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;



@Getter
public class Destinacija implements ValueObject {
//potrebno e da go imame kako valueObject bidejki ne smee da se pristapi i koristi od ticketCatalog
    private final DestinacijaId id;
    private final String name;
    private final String county;
   
    private Destinacija() {
        this.id= DestinacijaId.randomId(DestinacijaId.class);
        this.name= ""; 
        this.county="";
    }
    @JsonCreator
    public Destinacija(@JsonProperty("id") DestinacijaId id,
                       @JsonProperty("name") String name,
                       @JsonProperty("country") String country){
        this.id = id;
        this.name = name;
        this.county = country;
    }
}
