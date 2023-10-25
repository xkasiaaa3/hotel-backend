package com.tijo.kw.hotel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    UUID id;

    @Column(name="start_date")
    LocalDate startDate;

    @Column(name="end_date")
    LocalDate endDate;

    @Column(name="user_id")
    UUID userId;

    @Column(name="room_id")
    UUID roomId;

    @Column(name="all_inclusive")
    boolean allInclusive;

    @Column(name="price")
    double price;

}
