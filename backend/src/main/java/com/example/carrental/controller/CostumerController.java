package com.example.carrental.controller;


import com.example.carrental.dto.CostumerDto;
import com.example.carrental.service.CostumerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/costumer")
public class CostumerController {

    private CostumerService costumerService;

    @PostMapping("/save")
    public CostumerDto save(@RequestBody CostumerDto costumerDto){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        costumerDto.setPassword(passwordEncoder.encode(costumerDto.getPassword()));
        return costumerService.save(costumerDto);
    }

    @GetMapping("/list")
    public List<CostumerDto> findAll(){
        return costumerService.findAll();
    }

    @GetMapping("/{costumerId}")
    public CostumerDto findById(@PathVariable(name = "costumerId") long costumerId){
        return costumerService.findById(costumerId);
    }

    @PutMapping("/{costumerId}")
    public CostumerDto update(@RequestBody CostumerDto costumerDto,
                              @PathVariable(name = "costumerId") long costumerId){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        costumerDto.setPassword(passwordEncoder.encode(costumerDto.getPassword()));
        return costumerService.update(costumerDto, costumerId);
    }

    @DeleteMapping("{costumerId}")
    public String delete(@PathVariable(name = "costumerId") long costumerId){
        return costumerService.delete(costumerId);
    }
}