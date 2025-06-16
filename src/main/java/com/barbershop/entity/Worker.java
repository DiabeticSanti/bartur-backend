package com.barbershop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "worker")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String email;

    @Column( length = 20)
    private String phone;

    @Column(length = 100)
    private String specialty;

    @Column(length = 50)
    private String experience;

    @Column(nullable = false)
    private double rating;

    @Column(length = 255)
    private String avatar;


    @Column(nullable = false, length = 15)
    private boolean available;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BarberService> barberServices;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;
}
