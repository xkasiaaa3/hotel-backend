package com.tijo.kw.hotel.room.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Table(name="types_of_room")
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

    @Column(name = "photo_url")
    String photoUrl;

}
