package com.example.carrental.repository;


import com.example.carrental.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCarId(long carId);

    List<Review> findByCostumerId(long carId);

    List<Review> findByCarIdAndCostumerId(long carId, long costumerId);
}
