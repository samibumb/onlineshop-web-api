package org.fasttrackit.onlineshopapi.web;

import org.fasttrackit.onlineshopapi.dto.cart.AddProductToCartRequest;
import org.fasttrackit.onlineshopapi.dto.cart.CartResponse;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart/")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("update")
    public ResponseEntity addToCart(@RequestBody @Valid AddProductToCartRequest addProductToCartRequest) throws ResourceNotFoundException {

        cartService.addProductToCart(addProductToCartRequest);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable("customerId") Long id) throws ResourceNotFoundException {

        CartResponse cart = cartService.getCart(id);

        return new ResponseEntity<>(cart,HttpStatus.OK);
    }
}
