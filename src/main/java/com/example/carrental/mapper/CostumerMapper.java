package com.example.carrental.mapper;

import com.example.carrental.dto.CostumerDto;
import com.example.carrental.entity.Costumer;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CostumerMapper {

    public Costumer mapToEntity(CostumerDto costumerDto){
        Costumer costumer = new Costumer();
        costumer.setId(costumerDto.getId());
        costumer.setEmail(costumerDto.getEmail());
        costumer.setAddress(costumerDto.getAddress());
        costumer.setFirstName(costumerDto.getFirstName());
        costumer.setLastName(costumerDto.getLastName());
        costumer.setPhoneNumber(costumerDto.getPhoneNumber());
        costumer.setPassword(costumerDto.getPassword());
        costumer.setUsername(costumerDto.getUsername());
        costumer.setRoles(costumerDto.getRoleSet());

        return costumer;
    }

    public CostumerDto mapToDto(Costumer costumer){
        CostumerDto costumerDto = new CostumerDto();
        costumerDto.setId(costumer.getId());
        costumerDto.setEmail(costumer.getEmail());
        costumerDto.setAddress(costumer.getAddress());
        costumerDto.setFirstName(costumer.getFirstName());
        costumerDto.setLastName(costumer.getLastName());
        costumerDto.setPhoneNumber(costumer.getPhoneNumber());
        costumerDto.setUsername(costumer.getUsername());
        costumerDto.setPassword(costumer.getPassword());
        costumerDto.setRoleSet(costumer.getRoles());

        return costumerDto;
    }
}