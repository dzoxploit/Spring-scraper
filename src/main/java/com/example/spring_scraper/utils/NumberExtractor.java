package com.example.spring_scraper.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class NumberExtractor {
    

    private static final Pattern NUMBER_PATTERN =  Pattern.compile("\\d+");

    public static int extractNumber(String input){
        Matcher matcher = NUMBER_PATTERN.matcher(input);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }

        throw new IllegalArgumentException("No Number found in input" + input);
    }
}
