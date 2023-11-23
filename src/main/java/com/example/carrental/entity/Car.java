package com.example.carrental.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Brand cannot be blank")
    @Size(max = 50, message = "Brand cannot exceed 50 characters")
    @Column(name = "Brand")
    private String brand;

    @NotBlank(message = "Model cannot be blank")
    @Size(max = 50, message = "Model cannot exceed 50 characters")
    @Column(name = "Model")
    private String model;

    @NotBlank(message = "License Plate cannot be blank")
    @Size(max = 20, message = "License Plate cannot exceed 20 characters")
    @Column(name = "License_Plate")
    private String licensePlate;

    @NotNull(message = "Year cannot be null")
    @Column(name = "Year")
    private Year year;

    @NotBlank(message = "Color cannot be blank")
    @Size(max = 30, message = "Color cannot exceed 30 characters")
    @Column(name = "Color")
    private String color;

    @PositiveOrZero(message = "Mileage must be a positive number or zero")
    @Column(name = "Mileage")
    private double mileage;

    @NotBlank(message = "Status cannot be blank")
    @Size(max = 20, message = "Status cannot exceed 20 characters")
    @Column(name = "Status")
    private String status;

    @PositiveOrZero(message = "Amount must be a positive number or zero")
    @Column(name = "Amount")
    private double amount;

//    @OneToMany( mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Reservation> reservationSet = new HashSet<>();
//
//    @OneToMany( mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Review> reviewSet = new HashSet<>();
//
//    @ManyToOne
//    @JoinColumn(name = "carId", nullable = false)
//    private Category category;

}