package com.tijo.kw.hotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    UUID id;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @Column(name="email")
    String email;

    @Column(name="phone_number")
    String phoneNumber;

    @Column(name="role")
    String role;
}
