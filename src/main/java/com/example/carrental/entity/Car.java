package com.example.carrental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "Model")
    private String model;

    @Column(name = "License_Plate")
    private String licensePlate;
    @Column(name = "Year")
    private Year year;

    @Column(name = "Color")
    private String color;

    @Column(name = "Milage")
    private double milage;

    @Column(name = "Status")
    private String status;

    @Column(name = "Amount")
    private double amount;
}
