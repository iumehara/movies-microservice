package com.example.simplegateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.client.MockRestServiceServer.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withCreatedEntity;

@SpringBootTest
public class MovieServiceTest {
    @Autowired
    MovieService movieService;

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer server;

    @BeforeEach
    void setUp() {
        server = createServer(restTemplate);
    }

    @Test
    void create_callsBackendTraceInfoService() {
        // language=json
        String requestJson = "{\"title\": \"Forrest Gump\"}";
        String requestUrl = "http://localhost:8081/movies";
        HttpMethod requestMethod = POST;
        RequestMatcher requestHeaderMatcher = header("Content-Type", APPLICATION_JSON_UTF8.toString());

        // language=json
        String responseJson = "{\"id\": 1, \"title\": \"Forrest Gump\"}";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(APPLICATION_JSON_UTF8);

        server.expect(requestTo(requestUrl))
                .andExpect(content().json(requestJson))
                .andExpect(method(requestMethod))
                .andExpect(requestHeaderMatcher)

                .andRespond(withCreatedEntity(null).body(responseJson).headers(responseHeaders));


        Movie movie = movieService.create(new MovieArgs("Forrest Gump"));


        server.verify();
        assertThat(movie.getId()).isEqualTo(1);
        assertThat(movie.getTitle()).isEqualTo("Forrest Gump");
    }
}
