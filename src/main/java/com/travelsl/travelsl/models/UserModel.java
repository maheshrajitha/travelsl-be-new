package com.travelsl.travelsl.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UserModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(strategy = "uuid2",name = "uuid2")
    private String id;
    private String name;
    private String email;
    private String password;
    private Integer role;
}
