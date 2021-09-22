package com.happy.location_service.model;

public enum Country {
    CA("CA"),
    USA("USA");

    private String cityName;

    Country(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}
