package org.fasttrackit.onlineshopapi.repository;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //queries derived from method names
    Page<Product> findByNameContaining(String name, Pageable pageable);

    Page<Product> findByNameContainingAndQuantityGreaterThanEqual(String name,int minQuantity, Pageable pageable);

    //named query using jpql(Java Persistence Query Language)
//    @Query("SELECT product FROM Product product WHERE name like '%1'")
    //named query using native sql
    @Query(value = "SELECT * FROM Product WHERE name like '%1'",nativeQuery = true)
    Page<Product> findByPartialName(String name,Pageable pageable);
}
