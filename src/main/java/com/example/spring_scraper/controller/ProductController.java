package com.example.spring_scraper.controller;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.spring_scraper.model.Product;
import com.example.spring_scraper.model.ProductDetail;
import com.example.spring_scraper.service.TokopediaService;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final TokopediaService scraperService;

    public ProductController (TokopediaService scraperService){
        this.scraperService = scraperService;
    }

    @GetMapping("/scrape-tokopedia")
    
    public List<Product> scrapeTokopedia(@RequestParam String search) {
        return scraperService.getAllTokopedia(search);
    }


    @GetMapping("/scrape-tokopedia-detail")

    public List<ProductDetail> detailScrapeTokopedia(@RequestParam String url) {
        return scraperService.detailTokopediaProduct(url);
    }
}
