package com.example.demo.dto;

import com.example.demo.entity.NetworkUser;

import java.time.LocalDateTime;

public class NetworkUserDTO {
    private long id;
    private String nickname;
    private String firstName;
    private String lastName;
    private LocalDateTime dateCreated;
    private LocationDTO location;

    public NetworkUserDTO(NetworkUser u) {
        id = u.getId();
        nickname = u.getNickname();
        firstName = u.getFirstName();
        lastName = u.getLastName();
        dateCreated = u.getDateCreated();
        var loc = u.getLocation();
        if (loc != null) {
            location = new LocationDTO(u.getLocation());
        }
    }

    public NetworkUserDTO(String nickname, String firstName, String lastName, LocalDateTime dateCreated, LocationDTO location) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateCreated = dateCreated;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocationDTO getLocation() {
        return location;
    }
}
