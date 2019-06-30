package com.example.demo.dto;

import com.example.demo.entity.Location;

public class LocationDTO {
    private float longitude;
    private float latitude;
    private String city;
    private String country;

    LocationDTO(Location l) {
        longitude = l.getLongitude();
        latitude = l.getLatitude();
        city = l.getCity();
        country = l.getCountry();
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
