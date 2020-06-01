package com.travelsl.travelsl.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavouriteLocationDto {

    private String id;
    private String userId;
    private String locationId;
    private String image;
    private String country;
    private String city;

    public FavouriteLocationDto(String userId, String locationId, String image, String country, String city) {
        this.userId = userId;
        this.locationId = locationId;
        this.image = image;
        this.country = country;
        this.city = city;
    }
}
