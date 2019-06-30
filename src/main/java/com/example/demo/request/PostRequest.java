package com.example.demo.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostRequest {
    @NotNull
    @Size(max = 120, message = "Title must be less than 120 characters long")
    private String title;

    @NotNull
    @Size(max = 1000, message = "Description must be less than 1000 characters long")
    private String description;

    private LocationRequest location;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocationRequest getLocation() {
        return location;
    }
}
