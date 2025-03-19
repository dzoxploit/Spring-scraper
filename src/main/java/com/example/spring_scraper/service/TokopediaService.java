package com.example.spring_scraper.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import com.example.spring_scraper.model.Product;
import com.example.spring_scraper.model.ProductDetail;
import com.example.spring_scraper.config.NumberExtractorConfig;

@Service
public class TokopediaService {

    private static final String TOKPED_SEARCH_URL = "https://www.tokopedia.com/search?st=&q=";

    public List<Product> getAllTokopedia(String search) {
        
        List<Product> products = new ArrayList<>();

        String searchFinal = TOKPED_SEARCH_URL + search;

        System.err.println(searchFinal);
        
        try {
            Document doc = Jsoup.connect(searchFinal).userAgent("Mozila/5.0").get();
            
            Elements productElements = doc.select("div .css-jza1fo .css-5wh65g");

            for(Element productElement : productElements){
                String title = productElement.select("span").text();
                String price = productElement.select("a").attr("href");
                String imageUrl = productElement.select("img .css-1c345mg").attr("src");
                String productUrl = productElement.select("div").text();
                String countProduct = productElement.select("span").text();
                String shopName = productElement.select("span .T0rpy-LEwYNQifsgB").text();
                String placeShop = productElement.select("span").text();

                products.add(new Product(title, productUrl, imageUrl, price, shopName, placeShop, countProduct));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<ProductDetail> detailTokopediaProduct(String productUrl){
        
        List<ProductDetail> productDetail = new ArrayList<>();

        try {
            
            Document doc = Jsoup.connect(productUrl).userAgent("Mozila/5.0").get();
        
            String title = doc.select("h1[data-testid=lblPDPDetailProductName]").text();
            String category = doc.select("ul[data-testid=lblPDPInfoProduk]").text();
            String description = doc.select("div[data-testid=lblPDPDescriptionProduk]").text();
            String priceTemporary = doc.select("p[data-testid=pdpProductPrice]").text();
            String mainImageUrl = doc.select("img[data-testid=PDPMainImage]").attr("src");
            String countProduct = doc.select("p[data-testid=stock-label] b").text();
            String ratingProduct = doc.select("span[data-testid=lblPDPDetailProductRatingNumber]").text();
            String countRatingProduct = doc.select("span[data-testid=lblPDPDetailProductRatingNumber]").text();
            String shopName = doc.select("h2 .css-nc7wd7-unf-heading").text();

            productDetail.add(new ProductDetail(title, category, description, priceTemporary, mainImageUrl, countProduct, ratingProduct, countRatingProduct, shopName));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }        
        return productDetail;
    }
}
