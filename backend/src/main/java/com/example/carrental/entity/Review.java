package com.example.carrental.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Description cannot be null")
    @Size(min = 1, message = "Description must not be empty")
    private String description;

    @Min(value = 1, message = "Stars must be at least 1")
    @Max(value = 5, message = "Stars must be at most 5")
    private int stars;

    @ManyToOne
    @JoinColumn(name = "costumerId", nullable = false)
    private Costumer costumer;

    @ManyToOne
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

}
