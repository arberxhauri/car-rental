package com.example.carrental.mapper;

import com.example.carrental.dto.ReviewDto;
import com.example.carrental.entity.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewMapper {

    public Review mapToEntity(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setDescription(reviewDto.getDescription());
        review.setStars(reviewDto.getStars());

        return review;
    }

    public ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setStars(review.getStars());
        reviewDto.setCarId(review.getCar().getId());
        reviewDto.setCostumerId(review.getCostumer().getId());

        return reviewDto;
    }
}
