package com.example.simplegateway;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("movies")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Movie create(@RequestBody MovieArgs movieArgs) {
        return movieService.create(movieArgs);
    }
}
