package com.kh.baribari.product.domain;

import lombok.Data;

@Data
public class Cart {
    private int userNo;
    private int productNo;
    private int productQuantity;
    private Product product;

    public Cart(Product product) {
        this.product = product;
    }
    public Cart() {
        this.product = new Product();
    }

    public String getProductName() {
        return product.getProductName();
    }
    public void setProductName(String productName) {
        this.product.setProductName(productName);
    }
    public int getProductPrice() {
        return product.getProductPrice();
    }
    public void setProductPrice(int productPrice) {
        this.product.setProductPrice(productPrice);
    }
    public int getProductDiscount() {
        return product.getProductDiscount();
    }
    public void setProductDiscount(int productDiscount) {
        this.product.setProductDiscount(productDiscount);
    }
}
