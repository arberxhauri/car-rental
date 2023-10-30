package com.example.carrental.repository;

import com.example.carrental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    Optional<Car> findByLicensePlate(String licensePlate);

}
