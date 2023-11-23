package com.example.carrental.service;

import com.example.carrental.dto.CostumerDto;
import com.example.carrental.entity.Costumer;
import com.example.carrental.mapper.CostumerMapper;
import com.example.carrental.repository.CostumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CostumerService {

    private CostumerRepository costumerRepository;
    private CostumerMapper costumerMapper;

    public CostumerDto save(CostumerDto costumerDto){
        Costumer costumer = costumerRepository.save(costumerMapper.mapToEntity(costumerDto));
        return costumerMapper.mapToDto(costumer);
    }

    public List<CostumerDto> findAll(){
        List<Costumer> costumers = costumerRepository.findAll();

        return costumers.stream().map(costumer -> costumerMapper.mapToDto(costumer)).collect(Collectors.toList());
    }

    public CostumerDto findById(long costumerId){
        Costumer existingCostumer = costumerRepository.findById(costumerId).orElseThrow(() ->
                new RuntimeException("Costumer with id " + costumerId + " was not found!"));

        return costumerMapper.mapToDto(existingCostumer);
    }

    public CostumerDto update(CostumerDto costumerDto, long costumerId){
        Costumer existingCostumer = costumerRepository.findById(costumerId).orElseThrow(() ->
                new RuntimeException("Costumer with id " + costumerId + " was not found!"));

        existingCostumer.setId(costumerId);
        existingCostumer.setFirstName(costumerDto.getFirstName());
        existingCostumer.setLastName(costumerDto.getLastName());
        existingCostumer.setAddress(costumerDto.getAddress());
        existingCostumer.setPhoneNumber(costumerDto.getPhoneNumber());
        existingCostumer.setPassword(costumerDto.getPassword());
        existingCostumer.setRoles((costumerDto.getRoleSet()));

        Costumer savedCostumer = costumerRepository.save(existingCostumer);

        return costumerMapper.mapToDto(savedCostumer);
    }

    public String delete(long costumerId){
        Costumer existingCostumer = costumerRepository.findById(costumerId).orElseThrow(() ->
                new RuntimeException("Costumer with id " + costumerId + " was not found!"));
        costumerRepository.delete(existingCostumer);

        return "Costumer with id: "+ costumerId + " was deleted successfully";
    }
}