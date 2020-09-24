package com.kodilla.librarybackend.client;

import com.kodilla.librarybackend.configuration.GoogleBooksConfiguration;
import com.kodilla.librarybackend.domain.VolumeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class GoogleBooksClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleBooksClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GoogleBooksConfiguration googleBooksConfiguration;

    private URI urlBuild() {
        LOGGER.info("Building URI of GoogleBooks API started.");
        URI url = UriComponentsBuilder.fromHttpUrl(googleBooksConfiguration.getEndpoint() + "volumes?q=search-terms&key=" + googleBooksConfiguration.getApiKey()).build().encode().toUri();

        LOGGER.info("URI build: " + url);
        return url;
    }

    public List<VolumeDto> getGoogleBooks() {
        LOGGER.info("Getting Google books started");

        try {
            URI url = urlBuild();

            VolumeDto[] boardResponse = restTemplate.getForObject(url, VolumeDto[].class);
            LOGGER.info("Getting Google Books finished successfully. List size: " + Arrays.stream(boardResponse).collect(Collectors.toList()).size());
            return Stream.of(boardResponse).collect(Collectors.toList());

        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage());
            return new ArrayList<>();

        }
    }

}
