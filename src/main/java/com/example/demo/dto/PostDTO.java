package com.example.demo.dto;

import com.example.demo.entity.Post;

import java.time.LocalDateTime;

public class PostDTO {
    private long id;
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private LocationDTO location;

    public PostDTO(Post post) {
        id = post.getId();
        title = post.getTitle();
        description = post.getDescription();
        dateCreated = post.getDateCreated();
        var loc = post.getLocation();
        if (loc != null) {
            location = new LocationDTO(post.getLocation());
        }
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocationDTO getLocation() {
        return location;
    }
}
