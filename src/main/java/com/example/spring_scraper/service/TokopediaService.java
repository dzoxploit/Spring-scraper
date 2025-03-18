package com.example.spring_scraper.service;

import java.util.ArrayList;
import java.util.List;
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

    private static final String TOKPED_SEARCH_URL = "https://www.tokopedia.com/search?st=&q";

    public List<Product> getAllTokopedia(int page, int size, String search) {
        
        List<Product> products = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(TOKPED_SEARCH_URL).userAgent("Mozila/5.0").get();
            
            Elements productElements = doc.select("div .css-jza1fo .css-5wh65g");

            for(Element productElement : productElements){
                String title = productElement.select("span ._0T8-iGxMpV6NEsYEhwkqEg==").text();
                String productUrl = productElement.select("a oQ94Awb6LlTiGByQZo8Lyw== .IM26HEnTb-krJayD-R0OHw==").attr("href");
                String imageUrl = productElement.select("img .css-1c345mg .NWVIhquIvF0Jc0Qlizjluw==").attr("src");
                String price = productElement.select("div ._67d6E1xDKIzw+i2D2L0tjw==").text();
                String countProduct = productElement.select("span .se8WAnkjbVXZNA8mT+Veuw==").text();
                String shopName = productElement.select("span .T0rpy-LEwYNQifsgB").text();
                String placeShop = productElement.select("span .pC8DMVkBZGW7-egObcWMFQ==").text();

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
            NumberExtractorConfig config = NumberExtractorConfig.getInstance();
            
            Document doc = Jsoup.connect(productUrl).userAgent("Mozila/5.0").get();
        
            String title = doc.select("h1 .css-j63za0").text();
            String category = doc.select("li .css-1i6xy22 a b").text();
            String description = doc.select("div[data-testid=lblPDPDescriptionProduk]").text();
            String priceTemporary = doc.select("p[data-testid=pdpProductPrice]").text();
            Integer price = config.convertToInteger(priceTemporary);
            String mainImageUrl = doc.select("img[data-testid=PDPMainImage]").attr("src");
            String countProduct = doc.select("p[data-testid=lblPDPDetailProductSoldCounter]").text();
            String ratingProduct = doc.select("span[data-testid=lblPDPDetailProductRatingNumber]").text();
            String countRatingProductTemp = doc.select("span[data-testid=lblPDPDetailProductRatingCounter]").text();
            Integer countRatingProduct = config.convertToInteger(countRatingProductTemp);
            String shopName = doc.select("h2 .css-nc7wd7-unf-heading .e1qvo2ff2").text();

            productDetail.add(new ProductDetail(title, category, description, price, mainImageUrl, countProduct, ratingProduct, countRatingProduct, shopName));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }        
        return null;
    }
}
