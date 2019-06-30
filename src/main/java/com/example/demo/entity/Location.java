package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(schema = "demo", name = "location",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"longitude", "latitude"})})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "longitude", nullable = false)
    private float longitude;

    @Column(name = "latitude", nullable = false)
    private float latitude;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
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

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
