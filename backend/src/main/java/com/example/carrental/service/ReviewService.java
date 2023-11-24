package com.example.carrental.service;

import com.example.carrental.dto.ReviewDto;
import com.example.carrental.entity.Car;
import com.example.carrental.entity.Costumer;
import com.example.carrental.entity.Review;
import com.example.carrental.mapper.ReviewMapper;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.CostumerRepository;
import com.example.carrental.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;
    private CarRepository carRepository;
    private CostumerRepository costumerRepository;

    public ReviewDto save(ReviewDto reviewDto){
        Car existingCar = carRepository.findById(reviewDto.getCarId()).orElseThrow(() ->
                new RuntimeException("Car not found!"));
        Costumer existingCostumer = costumerRepository.findById(reviewDto.getCostumerId()).orElseThrow(() ->
                new RuntimeException("Costumer not found!"));
        Review review = reviewMapper.mapToEntity(reviewDto);
        review.setCar(existingCar);
        review.setCostumer(existingCostumer);
        Review savedReview = reviewRepository.save(review);

        return reviewMapper.mapToDto(savedReview);
    }

    public List<ReviewDto> findAll(){
        List<Review> reviewList = reviewRepository.findAll();

        return reviewList.stream().map(review -> reviewMapper.mapToDto(review)).collect(Collectors.toList());
    }

    public ReviewDto findById(long reviewId){
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(() ->
                new RuntimeException("Review with id " + reviewId + " was not found!"));

        return reviewMapper.mapToDto(existingReview);
    }

    public List<ReviewDto> findByCarId(long carId){
        Car existingCar = carRepository.findById(carId).orElseThrow(() ->
                new RuntimeException("Car with id " + carId + " was not found!"));

        List<Review> existingReviews = reviewRepository.findByCarId(carId);

        return existingReviews.stream().map(review -> reviewMapper.mapToDto(review)).collect(Collectors.toList());
    }

    public List<ReviewDto> findByCostumerId(long costumerId){
        Costumer existingCostumer = costumerRepository.findById(costumerId).orElseThrow(() ->
                new RuntimeException("Costumer with id " + costumerId + " was not found!"));

        List<Review> existingReviews = reviewRepository.findByCostumerId(costumerId);

        return existingReviews.stream().map(review -> reviewMapper.mapToDto(review)).collect(Collectors.toList());
    }

    public List<ReviewDto> findByCarIdAndCostumerId(long carId, long costumerId){
        Car existingCar = carRepository.findById(carId).orElseThrow(() ->
                new RuntimeException("Car with id " + carId + " was not found!"));

        Costumer existingCostumer = costumerRepository.findById(costumerId).orElseThrow(() ->
                new RuntimeException("Costumer with id " + costumerId + " was not found!"));

        List<Review> reviewList = reviewRepository.findByCarIdAndCostumerId(carId, costumerId);

        return reviewList.stream().map(review -> reviewMapper.mapToDto(review)).collect(Collectors.toList());
    }

    public String delete(long reviewId){
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(() ->
                new RuntimeException("Review with id " + reviewId + " was not found!"));

        reviewRepository.delete(existingReview);
        return "Review with id: " + reviewId + ", done for car: " +
                existingReview.getCar().getLicensePlate() + ", by costumer: " +
                existingReview.getCostumer().getFirstName() + " " +
                existingReview.getCostumer().getLastName() + "!";
    }
}
