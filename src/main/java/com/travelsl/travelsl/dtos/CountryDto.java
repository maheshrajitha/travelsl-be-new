package com.travelsl.travelsl.dtos;

import lombok.Data;

@Data
public class CountryDto {

    private String id;
    private String country;
    private String currency;
    private long population;
    private String description;
    private String image;
}
