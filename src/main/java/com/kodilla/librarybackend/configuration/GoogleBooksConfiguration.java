package com.kodilla.librarybackend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleBooksConfiguration {
    @Value("${googleBooks.endpoint}")
    private String endpoint;

    @Value("${googleBooks.api.key}")
    private String apiKey;

    public String getEndpoint() {
        return endpoint;
    }

    public String getApiKey() {
        return apiKey;
    }

}
