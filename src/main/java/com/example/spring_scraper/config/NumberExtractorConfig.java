package com.example.spring_scraper.config;

import com.example.spring_scraper.utils.NumberExtractor;

public class NumberExtractorConfig {

    private static NumberExtractorConfig instance;

    private NumberExtractorConfig() {}

    public static NumberExtractorConfig getInstance() {
        if (instance == null) {
            instance = new NumberExtractorConfig();
        }

        return instance;
    }

    public int convertToInteger(String input) {
        return NumberExtractor.extractNumber(input);
    }
    
}
