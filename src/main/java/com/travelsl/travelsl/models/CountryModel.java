package com.travelsl.travelsl.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CountryModel {

    @Id
    private String id;
    private String country;
    private String currency;
    private long population;
    private String description;
    private String image;
}
