package org.fasttrackit.onlineshopapi.dto;

public class GetProductsRequest {

    private String name;

    private Integer minQuantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    @Override
    public String toString() {
        return "GetProductsRequest{" +
                "name='" + name + '\'' +
                ", minQuantity=" + minQuantity +
                '}';
    }
}
