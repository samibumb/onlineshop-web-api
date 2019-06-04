package org.fasttrackit.onlineshopapi.web;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.dto.CreateProductRequest;
import org.fasttrackit.onlineshopapi.dto.GetProductsRequest;
import org.fasttrackit.onlineshopapi.dto.UpdateProductRequest;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products/")
public class ProductController {

    private final ProductService productservice;

    @Autowired
    public ProductController(ProductService productservice) {
        this.productservice = productservice;
    }

    //methods in controller are called EndPoints
    @GetMapping("getProducts")
    public ResponseEntity<Page<Product>> getProducts(GetProductsRequest getProductsRequest, Pageable pageable){

        Page<Product> response = productservice.getProducts(getProductsRequest,pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest){
//@valid=constraints de la model
        Product response = productservice.createProduct(createProductRequest);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id){
        productservice.deleteProduct(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Product product = productservice.getProduct(id);

        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity updateProduct(@PathVariable("id") Long id, @RequestBody @Valid UpdateProductRequest request) throws ResourceNotFoundException {

        productservice.updateProduct(id,request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
