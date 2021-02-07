package com.kodilla.librarybackend.client;

import com.kodilla.librarybackend.configuration.GoogleBooksConfiguration;
import com.kodilla.librarybackend.domain.VolumeDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleBooksClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleBooksClient.class);

    @Lazy
    @Autowired
    private RestTemplate restTemplate;

    @Lazy
    @Autowired
    private GoogleBooksConfiguration googleBooksConfiguration;


    public List <VolumeDto> getSpecifiedGoogleBooks(String title) {

        String stringUrl;
        String auth = "";
        String titl;
        String publishedDate;
        String description;

        List <VolumeDto> volumeDtoList = new ArrayList<>();

        try {
            UriComponents uri = UriComponentsBuilder
                    .fromHttpUrl(googleBooksConfiguration.getEndpoint() + "?q={title}&fields=items%28volumeInfo%28title,authors,publishedDate,description%29%29&key=" + googleBooksConfiguration.getKey())
                    .buildAndExpand(title);

            stringUrl = uri.toUriString();
            JSONObject jsonObject = readJsonFromUrl(stringUrl);
            JSONArray items = jsonObject.getJSONArray("items");


            for (int i = 0; i < items.length(); i++) {
                JSONObject volumeRecord = items.getJSONObject(i);
                JSONObject volumeInfo = volumeRecord.getJSONObject("volumeInfo");
                titl = volumeInfo.getString("title");
                JSONArray authors = new JSONArray();
                authors = volumeInfo.getJSONArray("authors");
                for (int j = 0; j < authors.length(); j++) {
                    auth += authors.getString(j) + ", ";
                }

                publishedDate = volumeInfo.getString("publishedDate");
                String noDescr = "No description";
                description = volumeInfo.optString("description").isEmpty()?noDescr:(volumeInfo.optString("description"));

                VolumeDto volumeDto = new VolumeDto();
                volumeDto.setTitle(titl);
                volumeDto.setAuthors(auth);
                auth = "";
                volumeDto.setPublishedDate(publishedDate);
                volumeDto.setDescription(description);
                LOGGER.info("{}",volumeDto.getDescription());
                volumeDtoList.add(volumeDto);

            }


        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOError e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return volumeDtoList;
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String jsonText = readAll(rd);
                JSONObject jsonObject = new JSONObject(jsonText);
                final JSONObject jsonObject1 = jsonObject;
                return jsonObject1;
            } finally {
                is.close();
            }
        }
    }

}


