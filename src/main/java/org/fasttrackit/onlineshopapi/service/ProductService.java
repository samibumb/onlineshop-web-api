package org.fasttrackit.onlineshopapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.dto.GetProductsRequest;
import org.fasttrackit.onlineshopapi.dto.UpdateProductRequest;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.repository.ProductRepository;
import org.fasttrackit.onlineshopapi.dto.CreateProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Product updateProduct(long id, UpdateProductRequest updateProductRequest) throws ResourceNotFoundException {
        LOGGER.info("Updating product {} with {}",id,updateProductRequest);

        Product product = getProduct(id);

        BeanUtils.copyProperties(updateProductRequest,product);

        return productRepository.save(product);
    }

    public void deleteProduct(long id){
    LOGGER.info("Deleting product {}",id);
        productRepository.deleteById(id);
    LOGGER.info("Deleted product {}",id);

    }

    public Page<Product> getProducts(GetProductsRequest getProductsRequest, Pageable pageable){

        if (getProductsRequest.getName()!=null && getProductsRequest.getMinQuantity()!=null) {
            return productRepository.findByNameContainingAndQuantityGreaterThanEqual(getProductsRequest.getName(),getProductsRequest.getMinQuantity(), pageable);
        }else if (getProductsRequest.getName()!=null){
            return productRepository.findByNameContaining(getProductsRequest.getName(),pageable);
        }


        return productRepository.findAll(pageable);
        }
}
