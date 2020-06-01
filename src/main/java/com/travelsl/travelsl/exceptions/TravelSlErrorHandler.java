package com.travelsl.travelsl.exceptions;

import org.springframework.http.HttpStatus;

public interface TravelSlErrorHandler {
    String getCode();
    String getMessage();
    boolean isVissible();
    HttpStatus getStatusCode();
}
