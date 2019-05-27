package org.fasttrackit.onlineshopapi.repository;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //queries derived from method names
    Page<Product> findByNameContaining(String name, Pageable pageable);

    Page<Product> findByNameContainingAndQuantityGreaterThanEqual(String name,int minQuantity, Pageable pageable);



}
