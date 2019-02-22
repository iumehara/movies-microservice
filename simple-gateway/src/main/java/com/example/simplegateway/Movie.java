package com.example.simplegateway;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Movie {
    private Integer id;
    private String title;

    public Movie(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
