package com.auth2.Exception;


public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Could not found the product with id "+ id);
    }
}