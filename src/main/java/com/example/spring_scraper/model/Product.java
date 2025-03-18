package com.example.spring_scraper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String title;
    private String param;
    private String price;
    private String imageUrl;
    private String productUrl;
    private Integer countProduct;
    private String shopName;
}
