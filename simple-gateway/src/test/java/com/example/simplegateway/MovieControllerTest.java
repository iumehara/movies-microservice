package com.example.simplegateway;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class MovieControllerTest {
    @Test
    public void create() throws Exception {
        MovieService movieService = mock(MovieService.class);
        when(movieService.create(any())).thenReturn(new Movie(1, "Forrest Gump"));
        MovieController movieController = new MovieController(movieService);
        MockMvc controller = standaloneSetup(movieController).build();


        // language=JSON
        String requestBody = "{\"title\":  \"Forrest Gump\"}";
        controller.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody))


                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", equalTo("Forrest Gump")));
        verify(movieService).create(new MovieArgs("Forrest Gump"));
    }
}
