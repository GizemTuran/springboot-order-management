package com.springboot;

public class InsufficientStock extends RuntimeException {
    public InsufficientStock(Long id) {
        super("Insufficient stock amount: " + id);
    }
}
