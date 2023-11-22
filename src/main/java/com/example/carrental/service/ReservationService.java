package com.example.carrental.service;

import com.example.carrental.dto.ReservationDto;
import com.example.carrental.entity.Reservation;
import com.example.carrental.mapper.ReservationMapper;
import com.example.carrental.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;

    public ReservationDto saveReservation(ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.mapToEntity(reservationDto);
        Reservation savedReservation = reservationRepository.save(reservation);

        return reservationMapper.mapToDto(savedReservation);
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
    public ReservationDto updateReservation(Long id, Reservation updatedReservation) {

        Reservation existingReservation = reservationRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Reservation with id: "+id+" not found!"));

        existingReservation.setReservation_id(updatedReservation.getReservation_id());
        existingReservation.setDateOfBooking(updatedReservation.getDateOfBooking());
        existingReservation.setDepartureDate(updatedReservation.getDepartureDate());
        existingReservation.setReturnDate(updatedReservation.getReturnDate());
        existingReservation.setAmount(updatedReservation.getAmount());

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
