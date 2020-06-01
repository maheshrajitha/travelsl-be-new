package com.travelsl.travelsl.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class LocationModel {
    @Id
    private String id;
    private String city;
    private String country;
    private String image;
    private String nearestAirport;
}
