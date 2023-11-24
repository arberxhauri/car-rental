package com.example.carrental.mapper;

import com.example.carrental.dto.CarDto;
import com.example.carrental.entity.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarMapper {

    public Car mapToEntity(CarDto carDto){
        Car car = new Car();
        car.setId(carDto.getId());
        car.setBrand(carDto.getBrand());
        car.setMileage(carDto.getMileage());
        car.setColor(carDto.getColor());
        car.setAmount(carDto.getAmount());
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setLicensePlate(carDto.getLicensePlate());
        car.setStatus(carDto.getStatus());

        return car;
    }

    public CarDto mapToDto(Car car){
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setBrand(car.getBrand());
        carDto.setMileage(car.getMileage());
        carDto.setColor(car.getColor());
        carDto.setAmount(car.getAmount());
        carDto.setModel(car.getModel());
        carDto.setYear(car.getYear());
        carDto.setLicensePlate(car.getLicensePlate());
        carDto.setStatus(car.getStatus());

        return carDto;
    }
}
