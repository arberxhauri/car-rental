package com.example.carrental.dto;

import com.example.carrental.entity.Category;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.UUID;

@Getter
@Setter
public class CarDto {

    private long id;

    @NotBlank(message = "Brand cannot be blank")
    @Size(max = 50, message = "Brand cannot exceed 50 characters")
    private String brand;

    @NotBlank(message = "Model cannot be blank")
    @Size(max = 50, message = "Model cannot exceed 50 characters")
    private String model;

    @NotBlank(message = "License Plate cannot be blank")
    @Size(max = 20, message = "License Plate cannot exceed 20 characters")
    private String licensePlate;

    @NotNull(message = "Year cannot be null")
    private Year year;

    @NotBlank(message = "Color cannot be blank")
    @Size(max = 30, message = "Color cannot exceed 30 characters")
    private String color;

    @PositiveOrZero(message = "Mileage must be a positive number or zero")
    private double mileage;

    @NotBlank(message = "Status cannot be blank")
    @Size(max = 20, message = "Status cannot exceed 20 characters")
    private String status;

    @PositiveOrZero(message = "Amount must be a positive number or zero")
    private double amount;

    private long categoryId;

    private CategoryDto categoryDto;
}
