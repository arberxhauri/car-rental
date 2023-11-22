package com.example.carrental.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ReservationDto {

    private long reservation_id;


    private LocalDate dateOfBooking;


    private long costumer_id;

    private UUID car_id;

    private LocalDate departureDate;

    private LocalDate returnDate;

    private double amount;

}