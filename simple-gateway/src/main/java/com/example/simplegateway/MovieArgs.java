package com.example.simplegateway;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieArgs {
    private String title;

    public MovieArgs(String title) {
        this.title = title;
    }
}
