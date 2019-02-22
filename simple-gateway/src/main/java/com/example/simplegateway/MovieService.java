package com.example.simplegateway;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class MovieService {
    private RestTemplate restTemplate;

    public MovieService(@Qualifier("defaultRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movie create(MovieArgs movieArgs) {
        String url = "http://localhost:8081";
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(url));
        ResponseEntity<Movie> movieResponseEntity = restTemplate.postForEntity("/movies", movieArgs, Movie.class);
        return movieResponseEntity.getBody();
    }
}

