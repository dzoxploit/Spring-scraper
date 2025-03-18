package com.example.spring_scraper.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
    private String title;
    private String category;
    private String description;
    private String price;
    private String mainImageUrl;
    private List<String> detailImageUrl;
    private String productUrl;
    private String countProduct;
    private String ratingProduct;
    private String countRatingProduct;
    private String shopName;
}
