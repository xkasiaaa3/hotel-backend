package com.tijo.kw.hotel.room.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="rooms")
public class Room {

    @Id
    UUID id;

    @Column(name="number")
    int number;

    @Column(name="type_id")
    UUID typeId;

    @Column(name="floor")
    int floor;

    @Column(name="photo_url")
    String photoUrl;
}
