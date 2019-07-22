package org.fasttrackit.onlineshopapi.service;

import org.fasttrackit.onlineshopapi.domain.Cart;
import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.dto.cart.AddProductToCartRequest;
import org.fasttrackit.onlineshopapi.dto.cart.CartResponse;
import org.fasttrackit.onlineshopapi.dto.customer.CustomerDto;
import org.fasttrackit.onlineshopapi.dto.product.ProductResponse;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CustomerService customerService;

    private final CartRepository cartRepository;

    private final ProductService productService;

    @Autowired
    public CartService(CustomerService customerService, CartRepository cartRepository, ProductService productService) {
        this.customerService = customerService;
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Transactional
    public void addProductToCart(AddProductToCartRequest request) throws ResourceNotFoundException {
        LOGGER.info("Saving cart {}",request);

        Cart cart = cartRepository.findById(request.getCustomerId()).orElse(new Cart());
        cart.setCustomer(customerService.getCustomer(request.getCustomerId()));

        Product product = productService.getProduct(request.getProductId());

        cart.addProduct(product);

        cartRepository.save(cart);

    }

    @Transactional
    public CartResponse getCart(Long id) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cart "+id+" not found."));

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(cart.getCustomer().getId());
        customerDto.setFirstName(cart.getCustomer().getFirstName());
        customerDto.setLastName(cart.getCustomer().getLastName());

        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());
        cartResponse.setCustomerDto(customerDto);

        cart.getProducts().forEach(product -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());

            cartResponse.getProductResponses().add(productResponse);
        });
        return cartResponse;
    }



}
