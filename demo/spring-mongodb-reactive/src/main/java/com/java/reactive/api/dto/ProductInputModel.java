package com.java.reactive.api.dto;

import lombok.Data;

@Data
public class ProductInputModel {
    private String id;
    private String name;
    private int qty;
    private double price;
}
