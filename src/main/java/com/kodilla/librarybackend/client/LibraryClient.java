package com.kodilla.librarybackend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LibraryClient {

    @Autowired
    private RestTemplate restTemplate;


}
