package org.fasttrackit.onlineshopapi.dto.cart;

import org.fasttrackit.onlineshopapi.dto.customer.CustomerDto;
import org.fasttrackit.onlineshopapi.dto.product.ProductResponse;

import java.util.HashSet;
import java.util.Set;

public class CartResponse {

    private Long id;

    private CustomerDto customerDto;

    private Set<ProductResponse> productResponses=new HashSet<>();
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public Set<ProductResponse> getProductResponses() {
        return productResponses;
    }

    public void setProductResponses(Set<ProductResponse> productResponses) {
        this.productResponses = productResponses;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "id=" + id +
                ", customerDto=" + customerDto +
                '}';
    }
}
