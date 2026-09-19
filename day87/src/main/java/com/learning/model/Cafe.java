package com.learning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private double rating;
    private boolean hasWifi;

    @Enumerated(EnumType.STRING)
    private PriceLevel priceLevel;

    private String description;

    protected Cafe() {
    }

    public Cafe(String name, String address, double latitude, double longitude, double rating,
                boolean hasWifi, PriceLevel priceLevel, String description) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.hasWifi = hasWifi;
        this.priceLevel = priceLevel;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRating() {
        return rating;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public PriceLevel getPriceLevel() {
        return priceLevel;
    }

    public String getDescription() {
        return description;
    }
}
