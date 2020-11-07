package com.kodilla.librarybackend.client;

import com.kodilla.librarybackend.configuration.GoogleBooksConfiguration;
import com.kodilla.librarybackend.domain.VolumeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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


    public List<VolumeDto> getAllGoogleBooks() {
        LOGGER.info("Getting ALL Google books started");

        try {

            LOGGER.info("Building URI of ALL GoogleBooks API started.");
            URI url = UriComponentsBuilder.fromHttpUrl(googleBooksConfiguration.getEndpoint()).build().encode().toUri();
            LOGGER.info("All Google Books URI builded");

            VolumeDto[] boardResponse = restTemplate.getForObject(url, VolumeDto[].class);
            LOGGER.info("Getting All Google Books finished successfully. List size: " + Arrays.stream(boardResponse).collect(Collectors.toList()).size());
            return Stream.of(boardResponse).collect(Collectors.toList());
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage());

        }
        return new ArrayList<VolumeDto>();
    }

    public VolumeDto getSpecifiedGoogleBooks(String book) {
        LOGGER.info("Getting Specific Google books started");

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("book", book);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);

            LOGGER.info("Building URI of Specific GoogleBook API started.");
            URI url = UriComponentsBuilder.fromHttpUrl(googleBooksConfiguration.getEndpoint() + "?q=" + entity.getHeaders()).build().encode().toUri(); /*+ "&kyes&key=" + googleBooksConfiguration.getApiKey())*/
            LOGGER.info("URI builded");

            ResponseEntity<VolumeDto> respEntity = (restTemplate.exchange(url, HttpMethod.GET, entity, VolumeDto.class));
            LOGGER.info("Getting Specified Google Book finished successfully.");
            return respEntity.getBody();

        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage());
            return new VolumeDto();

        }

    }
}


