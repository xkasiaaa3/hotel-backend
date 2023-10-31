package com.tijo.kw.hotel.room.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="types_of_rooms")
public class TypeOfRoom {

    @Id
    UUID id;

    @Column(name="name")
    String name;

    @Column(name="number_of_people")
    int numberOfPeople;

    @Column(name="number_of_beds")
    int numberOfBeds;

    @Column(name="bathroom")
    boolean bathroom;

    @Column(name="balcony")
    boolean balcony;

    @Column(name="price_per_day")
    double pricePerDay;

}
