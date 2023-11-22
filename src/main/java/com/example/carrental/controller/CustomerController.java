package com.example.carrental.controller;
import com.example.carrental.dto.CustomerDto;
import com.example.carrental.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/CarRents")
public class CustomerController {

    private CustomerService customerService;

    @PostMapping("/save")
    public CustomerDto save(@Valid @RequestBody CustomerDto customerDto) {
        return customerService.save(customerDto);
    }

    @GetMapping
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{customerId}")
    public CustomerDto findById(@PathVariable(name = "customerId") Long customerId) {
        return customerService.findById(customerId);
    }

    @PutMapping("/{customerId}")
    public CustomerDto update(@Valid @RequestBody CustomerDto customerDto, @PathVariable(name = "customerId") Long customerId) {
        return customerService.update(customerDto,customerId);
    }


    @DeleteMapping("/{customerId}")
    public String delete(@PathVariable(name = "customerId") Long customerId) {
        customerService.delete(customerId);
        return "Customer successfully deleted!";
    }


}
