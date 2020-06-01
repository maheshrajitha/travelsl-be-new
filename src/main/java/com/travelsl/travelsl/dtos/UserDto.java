package com.travelsl.travelsl.dtos;

import lombok.Data;

@Data
public class UserDto {

    private String email;
    private String name;
    private String password;
    private Integer role;
    private String id;
}
