package com.example.spring_scraper.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewProduct {
    private String title;
    private String datePost;
    private String productUrl;
    private String countProduct;
    private String shopName;
    private List<String> imageReview;
}
