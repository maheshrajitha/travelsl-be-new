package com.travelsl.travelsl.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class FavouriteLocationModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(strategy = "uuid2",name = "uuid2")
    private String id;
    private String userId;
    private String locationId;
}
