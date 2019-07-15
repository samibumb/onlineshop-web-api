package org.fasttrackit.onlineshopapi.repository;

import org.fasttrackit.onlineshopapi.domain.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ProductReview,Long> {

    //queries by nested properties
    Page<ProductReview> findByProductId(Long productId, Pageable pageable);

}
