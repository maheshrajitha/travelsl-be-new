package com.travelsl.travelsl.dtos;

import lombok.Data;

@Data
public class TokenDto {

    private String id;
    private String userId;
    private Integer role;
    private Long issuedDate;
    private String issuedBy = "Travelsl";
}
