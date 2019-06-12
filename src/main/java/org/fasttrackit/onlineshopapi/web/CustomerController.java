package org.fasttrackit.onlineshopapi.web;

import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.dto.CreateCustomerRequest;
import org.fasttrackit.onlineshopapi.dto.UpdateCustomerRequest;
import org.fasttrackit.onlineshopapi.dto.UpdateProductRequest;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers/")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("create")
    public ResponseEntity<Customer> create(@RequestBody CreateCustomerRequest customer){

        Customer response = customerService.createCustomer(customer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Customer>> getCustomers(){

        List<Customer> response = customerService.getCustomers();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) throws ResourceNotFoundException {

        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("updateById/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") Long id , @RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) throws ResourceNotFoundException {

        customerService.updateCustomer(id,updateCustomerRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
