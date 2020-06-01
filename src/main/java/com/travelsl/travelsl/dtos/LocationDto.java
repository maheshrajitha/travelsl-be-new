package com.travelsl.travelsl.dtos;

import lombok.Data;

@Data
public class LocationDto {

    private String image;
    private String id;
    private String city;
    private String country;
    private String nearestAirport;
}
