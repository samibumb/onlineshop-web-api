package org.fasttrackit.onlineshopapi.service;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.repository.ProductRepository;
import org.fasttrackit.onlineshopapi.transfer.CreateProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(CreateProductRequest createProductRequest){

        LOGGER.info("Creating product {}",createProductRequest);
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setQuantity(createProductRequest.getQuantity());
        product.setPrice(createProductRequest.getPrice());
        product.setImage(createProductRequest.getImage());

        return productRepository.save(product);
    }

    public Product getProduct(long id) throws ResourceNotFoundException {

        return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product "+id+" does not exist"));


    }
}
