package com.example.carrental.service;

import com.example.carrental.dto.ReservationDto;
import com.example.carrental.entity.Car;
import com.example.carrental.entity.Costumer;
import com.example.carrental.entity.Reservation;
import com.example.carrental.mapper.ReservationMapper;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.CostumerRepository;
import com.example.carrental.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private CarRepository carRepository;
    private CostumerRepository costumerRepository;

    public ReservationDto saveReservation(ReservationDto reservationDto) {
        Car existingCar = carRepository.findById(reservationDto.getCarId()).orElseThrow(() ->
                new RuntimeException("Car not found!"));
        Costumer existingCostumer = costumerRepository.findById(reservationDto.getCostumerId()).orElseThrow(() ->
                new RuntimeException("Costumer not found!"));
        Reservation reservation = reservationMapper.mapToEntity(reservationDto);
        reservation.setCar(existingCar);
        reservation.setCostumer(existingCostumer);


        double amount = calculateAmount(reservationDto.getDepartureDate(),
                reservationDto.getReturnDate(),existingCar.getAmount());
        reservation.setAmount(amount);

        Reservation savedReservation = reservationRepository.save(reservation);

        return reservationMapper.mapToDto(savedReservation);
    }
    public double calculateAmount(LocalDate departureDate,LocalDate returnDate,double amount){

        Period period = Period.between(departureDate,returnDate);
        return period.getDays()*amount;
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();

        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for (Reservation reservation: reservationList){
            reservationDtoList.add(reservationMapper.mapToDto(reservation));
        }
        return reservationDtoList;
    }

    public ReservationDto getReservationById(Long id) {

        Reservation existingReservation = reservationRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Reservation with id:"+id+" not found") );

        return reservationMapper.mapToDto(existingReservation);


    }
    public ReservationDto updateReservation(Long id, ReservationDto updatedReservation) {

        Reservation existingReservation = reservationRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Reservation with id: "+id+" not found!"));

        existingReservation.setReservation_id(id);
        existingReservation.setDateOfBooking(updatedReservation.getDateOfBooking());
        existingReservation.setDepartureDate(updatedReservation.getDepartureDate());
        existingReservation.setReturnDate(updatedReservation.getReturnDate());
        existingReservation.getCostumer().setId(updatedReservation.getCostumerId());
        existingReservation.getCar().setId(updatedReservation.getCarId());

        double amount = calculateAmount(updatedReservation.getDepartureDate(),
                updatedReservation.getReturnDate(),existingReservation.getCar().getAmount());
        existingReservation.setAmount(amount);

        Reservation savedReservation = reservationRepository.save(existingReservation);
        return reservationMapper.mapToDto(savedReservation);


    }

    public void deleteReservation(Long id) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);

        if (existingReservation.isPresent()){
            reservationRepository.delete(existingReservation.get());
        }else {throw new RuntimeException("Reservation not found with ID: " + id);}
    }



}
