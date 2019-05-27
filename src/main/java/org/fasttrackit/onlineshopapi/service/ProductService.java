package org.fasttrackit.onlineshopapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    public Product createProduct(CreateProductRequest createProductRequest){

        LOGGER.info("Creating product {}",createProductRequest);
//        Product product = new Product();
//        product.setName(createProductRequest.getName());
//        product.setQuantity(createProductRequest.getQuantity());
//        product.setPrice(createProductRequest.getPrice());
//        product.setImage(createProductRequest.getImage());

        //same result as above with objectMapper
        Product product = objectMapper.convertValue(createProductRequest,Product.class);

        return productRepository.save(product);
    }

    public Product getProduct(long id) throws ResourceNotFoundException {

        return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product "+id+" does not exist"));


    }
}
