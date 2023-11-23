package com.example.carrental.service;

import com.example.carrental.dto.CarDto;
import com.example.carrental.entity.Car;
//import com.example.carrental.entity.Category;
import com.example.carrental.mapper.CarMapper;
import com.example.carrental.repository.CarRepository;
//import com.example.carrental.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CarService {

    private CarRepository carRepository;
    private CarMapper carMapper;
//    private CategoryRepository categoryRepository;

    public CarDto save(CarDto carDto) {
        Car car = carMapper.mapToEntity(carDto);

        Car savedCar = carRepository.save(car);
        return carMapper.mapToDto(savedCar);
    }

    public List<CarDto> findAll() {
        List<Car> allCars = carRepository.findAll();

        return allCars.stream().map(car -> carMapper.mapToDto(car)).collect(Collectors.toList());
    }

//    public List<CarDto> findByCategoryId(UUID categoryId) {
//        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
//                new RuntimeException("Category with id: " + categoryId + " was not"));
//
//        List<Car> carList = carRepository.findByCategoryId(categoryId);
//
//        return carList.stream().map(car -> carMapper.mapToDto(car)).collect(Collectors.toList());
//    }

    public CarDto findById(long carId) {
        Car existingCar = carRepository.findById(carId).orElseThrow(() ->
                new RuntimeException("Car with id: " + carId + " was not found!"));

        return carMapper.mapToDto(existingCar);
    }

    public CarDto updateCar(CarDto carDto, long carId) {
        Car existingCar = carRepository.findById(carId).orElseThrow(() ->
                new RuntimeException("Car with id: " + carId + " was not found!"));

        Car car = carMapper.mapToEntity(carDto);
//        car.setId(carId);
//        car.setBrand(carDto.getBrand());
//        car.setModel(carDto.getModel());
//        car.setLicensePlate(carDto.getLicensePlate());
//        car.setYear(carDto.getYear());
//        car.setStatus(carDto.getStatus());
//        car.setAmount(carDto.getAmount());
//        car.setMileage(carDto.getMileage());
//        car.setColor(carDto.getColor());

        Car savedCar = carRepository.save(car);

        return carMapper.mapToDto(savedCar);
    }

    public String deleteCar(long carId) {
        Car existingCar = carRepository.findById(carId).orElseThrow(() ->
                new RuntimeException("Car with id: " + carId + " was not found!"));
        carRepository.delete(existingCar);
        return "Car with id" + carId + " was deleted successfully";
    }
}
