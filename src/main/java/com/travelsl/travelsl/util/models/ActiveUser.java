package com.travelsl.travelsl.util.models;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Data
@Component
public class ActiveUser {

    private String userId;
    private Integer role;
}
