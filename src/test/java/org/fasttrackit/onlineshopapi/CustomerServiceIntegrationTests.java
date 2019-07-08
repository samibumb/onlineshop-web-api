package org.fasttrackit.onlineshopapi;

import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.dto.CreateCustomerRequest;
import org.fasttrackit.onlineshopapi.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTests {

	@Autowired
	private CustomerService customerService;

	@Test
	public void testCreateCustomer_whenValidRequest_thenReturnCustomer(){

		CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();

		createCustomerRequest.setFirstName("Dorel");
		createCustomerRequest.setLastName("Tarnacop");
		createCustomerRequest.setAge(100);
		createCustomerRequest.seteMail("dorel.tarnacop");
		createCustomerRequest.setPhoneNumber(746573829L);

		Customer customer =customerService.createCustomer(createCustomerRequest);

		assertThat(customer,notNullValue());
		assertThat(customer.getId(),greaterThan(0L));
		assertThat(customer.getFirstName(),is(createCustomerRequest.getFirstName()));
		assertThat(customer.getLastName(),is(createCustomerRequest.getLastName()));
		assertThat(customer.getAge(),is(createCustomerRequest.getAge()));
		assertThat(customer.geteMail(),is(createCustomerRequest.geteMail()));
		assertThat(customer.getPhoneNumber(),is(createCustomerRequest.getPhoneNumber()));

	}
}
