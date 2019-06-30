package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(schema = "demo", name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    public Location() {
    }

    public Location(float longitude, float latitude, String city, String country) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.country = country;
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
