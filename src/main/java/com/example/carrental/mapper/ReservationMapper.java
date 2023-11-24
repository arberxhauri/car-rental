package com.example.carrental.mapper;

import com.example.carrental.dto.ReservationDto;
import com.example.carrental.entity.Reservation;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationMapper {

    private CarRepository carRepository;
    private CarMapper carMapper;


    public Reservation mapToEntity(ReservationDto reservationDto){
        Reservation reservation  =new Reservation();
        reservation.setReservation_id(reservationDto.getReservation_id());
        reservation.setDateOfBooking(reservationDto.getDateOfBooking());
        reservation.setDepartureDate(reservationDto.getDepartureDate());
        reservation.setReturnDate(reservationDto.getReturnDate());

        return reservation;

    }

    public ReservationDto mapToDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservation_id(reservation.getReservation_id());
        reservationDto.setDateOfBooking(reservation.getDateOfBooking());
        reservationDto.setDepartureDate(reservation.getDepartureDate());
        reservationDto.setReturnDate(reservation.getReturnDate());
        reservationDto.setCostumerId(reservation.getCostumer().getId());
        reservationDto.setAmount(reservation.getAmount());
        reservationDto.setCarId(reservation.getCar().getId());

        return reservationDto;
    }

}