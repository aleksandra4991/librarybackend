package com.kodilla.librarybackend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleBooksConfiguration {
    @Value("${googleBooks.endpoint}")
    private String endpoint;

    @Value("${key}")
    private String key;

    public String getEndpoint() {
        return endpoint;
    }

    public String getKey() {
        return key;
    }

}
