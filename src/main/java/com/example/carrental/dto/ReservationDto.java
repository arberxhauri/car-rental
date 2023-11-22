package com.example.carrental.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDto {

    private long reservation_id;


    private LocalDate dateOfBooking;

//    @ManyToOne
//    @JoinColumn(name = "CLIENT_ID",nullable = false)
//    private Client client;
//
//    @ManyToOne
//    @JoinColumn(name = "CAR_ID",nullable = false)
//    private Car car;

    private LocalDate departureDate;

    private LocalDate returnDate;

    private double amount;

}