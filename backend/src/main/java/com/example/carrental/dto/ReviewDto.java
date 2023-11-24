package com.example.carrental.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private long id;

    @NotNull(message = "Description cannot be null")
    @Size(min = 1, message = "Description must not be empty")
    private String description;

    @Min(value = 1, message = "Stars must be at least 1")
    @Max(value = 5, message = "Stars must be at most 5")
    private int stars;

    private long carId;

    private long costumerId;
}
