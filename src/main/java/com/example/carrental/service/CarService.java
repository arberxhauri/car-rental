package com.example.carrental.service;

import com.example.carrental.entity.Car;
import com.example.carrental.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CarService {

    private CarRepository carRepository;

    public Car save(Car newCar){
        Car savedCar = carRepository.save(newCar);
        return savedCar;
    }
}
