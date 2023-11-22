package com.example.carrental.service;

import com.example.carrental.dto.CustomerDto;
import com.example.carrental.entity.Customer;
import com.example.carrental.mapper.CustomerMapper;
import com.example.carrental.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = customerMapper.mapToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.mapToDto(savedCustomer) ;
    }

    public List<CustomerDto> findAll() {
        List<Customer> customerList = customerRepository.findAll();

        return customerList.stream().map(customer -> customerMapper.mapToDto(customer)).collect(Collectors.toList());
    }

    public CustomerDto findById(Long customerId) {

        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(()->
                new RuntimeException("Customer with id: "+customerId+" was not found in the database."));
        return customerMapper.mapToDto(existingCustomer);
    }

    public CustomerDto update(CustomerDto customerDto, Long customerId) {
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(() ->
                new RuntimeException("Customer not found with ID: " + customerId));
        existingCustomer.setId(customerId);
        existingCustomer.setFirst_name(customerDto.getFirst_name());
        existingCustomer.setLast_name(customerDto.getLast_name());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setPassword(customerDto.getPassword());
        existingCustomer.setAge(customerDto.getAge());
        existingCustomer.setPhoneNumber(customerDto.getPhone_number());
        existingCustomer.setUsername(customerDto.getUsername());
        Customer savedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.mapToDto(savedCustomer);
    }

    public void delete(Long customerId) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);

        if (existingCustomer.isPresent()){
            customerRepository.delete(existingCustomer.get());
        }else {throw new RuntimeException("Customer not found with ID: " + customerId);}
    }


}