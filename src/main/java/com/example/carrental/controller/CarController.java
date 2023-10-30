package com.example.carrental.controller;

import com.example.carrental.entity.Car;
import com.example.carrental.service.CarService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;

    @PostMapping("/save")
    public Car save(@Valid @RequestBody Car newCar){
        newCar.setId(UUID.randomUUID());
        return carService.save(newCar);
    }
}
