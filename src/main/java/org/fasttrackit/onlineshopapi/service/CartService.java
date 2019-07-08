package org.fasttrackit.onlineshopapi.service;

import org.fasttrackit.onlineshopapi.domain.Cart;
import org.fasttrackit.onlineshopapi.dto.cart.AddProductToCartRequest;
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

    @Autowired
    public CartService(CustomerService customerService, CartRepository cartRepository) {
        this.customerService = customerService;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void addProductToCart(AddProductToCartRequest request) throws ResourceNotFoundException {
        LOGGER.info("Saving cart {}",request);

        Cart cart = new Cart();
        cart.setCustomer(customerService.getCustomer(request.getCustomerId()));

    cartRepository.save(cart);

    }

}
