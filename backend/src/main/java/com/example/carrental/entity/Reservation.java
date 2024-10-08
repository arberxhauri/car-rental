package com.example.carrental.entity;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter

@Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservation_id;

    // @NotEmpty(message = "Date of booking must not be empty")
    @NotNull
    @Column(name = "DATE_OF_BOOKING",nullable = false)
    @Valid
    private LocalDate dateOfBooking;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID",nullable = false)
    private Costumer costumer;

    @ManyToOne
    @JoinColumn(name = "CAR_ID",nullable = false)
    private Car car;

    //@NotEmpty(message = "Departure date must not be empty")
    @NotNull
    @Column(name = "DEPARTURE_DATE",nullable = false)
    @Valid
    private LocalDate departureDate;

    //@NotEmpty(message = "Return Date must not be empty")
    @NotNull
    @Column(name = "RETURN_DATE",nullable = false)
    @Valid
    private LocalDate returnDate;

    @Column(name = "AMOUNT",nullable = false)
    private double amount;

    public void validate() throws IllegalArgumentException {
        if (dateOfBooking == null || departureDate == null || returnDate == null) {
            throw new IllegalArgumentException("Date fields cannot be null");
        }

        if (departureDate.isAfter(returnDate)) {
            throw new IllegalArgumentException("Invalid date range. 'dateFrom' must be before or equal to 'dateTo'");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("'amountPerDay' must be a non-negative value");
        }
    }

}