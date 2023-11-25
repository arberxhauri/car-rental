package com.example.carrental.controller;

import com.example.carrental.dto.ReviewDto;
import com.example.carrental.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping("/save")
    public ReviewDto save(@RequestBody ReviewDto reviewDto){
        return reviewService.save(reviewDto);
    }

    @GetMapping("/list")
    public List<ReviewDto> findAll(){
        return reviewService.findAll();
    }

    @GetMapping("/{reviewId}")
    public ReviewDto findById(@PathVariable(name = "reviewId") long reviewId){
        return reviewService.findById(reviewId);
    }

    @GetMapping("/findByCar/{carId}")
    public List<ReviewDto> findByCarId(@PathVariable(name = "carId") long carId){
        return reviewService.findByCarId(carId);
    }

    @GetMapping("/findByCostumer/{costumerId}")
    public List<ReviewDto> findByCostumerId(@PathVariable(name = "costumerId") long costumerId){
        return reviewService.findByCostumerId(costumerId);
    }

    @GetMapping("/{carId}/{costumerId}")
    public List<ReviewDto> findByCarIdAndCostumerId(@PathVariable(name = "carId") long carId,
                                                    @PathVariable(name = "costumerId") long costumerId){
        return reviewService.findByCarIdAndCostumerId(carId, costumerId);
    }

    @DeleteMapping("/delete/{reviewId}")
    public String delete(@PathVariable(name = "reviewId") long reviewId){
        return reviewService.delete(reviewId);
    }
}
