package org.fasttrackit.onlineshopapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.dto.CreateCustomerRequest;
import org.fasttrackit.onlineshopapi.dto.UpdateCustomerRequest;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper=objectMapper;

    }

    public Customer createCustomer(CreateCustomerRequest createCustomerRequest){

        Customer customer = objectMapper.convertValue(createCustomerRequest,Customer.class);

        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers(){

        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id) throws ResourceNotFoundException {

        return customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer"+ id+" does not exist"));
    }

    public void deleteCustomer(Long id){

        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Long id, UpdateCustomerRequest updateCustomerRequest) throws ResourceNotFoundException {
        Customer customer= getCustomer(id);

        BeanUtils.copyProperties(updateCustomerRequest,customer);

        return customerRepository.save(customer);
    }
}
