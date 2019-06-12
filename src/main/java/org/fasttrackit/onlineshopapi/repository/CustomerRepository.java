package org.fasttrackit.onlineshopapi.repository;

import org.fasttrackit.onlineshopapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    List<Customer> findAll();


}
