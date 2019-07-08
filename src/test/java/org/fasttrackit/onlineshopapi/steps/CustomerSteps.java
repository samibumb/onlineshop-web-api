package org.fasttrackit.onlineshopapi.steps;

import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.dto.CreateCustomerRequest;
import org.fasttrackit.onlineshopapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class CustomerSteps {

    @Autowired
    private CustomerService customerService;

    public Customer createCustomer(){

        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();

        createCustomerRequest.setFirstName("Gigel");
        createCustomerRequest.setLastName("Topor");
        createCustomerRequest.setAge(45);
        createCustomerRequest.seteMail("toporultetaie@yahoo.com");
        createCustomerRequest.setPhoneNumber(746573725L);

        Customer customer =customerService.createCustomer(createCustomerRequest);

        assertThat(customer,notNullValue());
        assertThat(customer.getId(),greaterThan(0L));
        assertThat(customer.getFirstName(),is(createCustomerRequest.getFirstName()));
        assertThat(customer.getLastName(),is(createCustomerRequest.getLastName()));
        assertThat(customer.getAge(),is(createCustomerRequest.getAge()));
        assertThat(customer.geteMail(),is(createCustomerRequest.geteMail()));
        assertThat(customer.getPhoneNumber(),is(createCustomerRequest.getPhoneNumber()));

        return customer;
    }
}
