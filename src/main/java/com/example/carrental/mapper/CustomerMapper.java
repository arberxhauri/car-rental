package com.example.carrental.mapper;

import com.example.carrental.dto.CustomerDto;
import com.example.carrental.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirst_name(customerDto.getFirst_name());
        customer.setLast_name(customerDto.getLast_name());
        customer.setUsername(customerDto.getUsername());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setAge(customerDto.getAge());
        customer.setAddress(customerDto.getAddress());
        customer.setPhoneNumber(customerDto.getPhone_number());

        return customer;
    }

    public CustomerDto mapToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirst_name(customer.getFirst_name());
        customerDto.setLast_name(customer.getLast_name());
        customerDto.setUsername(customer.getUsername());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());
        customerDto.setAge(customer.getAge());
        customerDto.setAddress(customer.getAddress());
        customerDto.setPhone_number(customer.getPhoneNumber());

        return customerDto;
    }
}

