package com.example.demo.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LocationRequest {
    private float longitude;
    private float latitude;

    @NotNull
    @Size(max = 120, message = "City must be less than 120 characters long")
    private String city;

    @NotNull
    @Size(max = 120, message = "Country must be less than 120 characters long")
    private String country;

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
