package com.travelsl.travelsl.exceptions.exceptionmodels;

import com.travelsl.travelsl.exceptions.TravelSlErrorHandler;
import org.springframework.http.HttpStatus;

public enum LocationExceptionModel implements TravelSlErrorHandler {
    LOCATION_EXISTS_INFAVOURITES("This location exists",true,HttpStatus.CONFLICT),
    WEATHER_FETCHING_ERROR("Weather Fetching Error",true,HttpStatus.INTERNAL_SERVER_ERROR),
    NO_FAVOURITE_LOCATIONS("Favourite Location",true,HttpStatus.NOT_FOUND)
    ;

    private String message;
    private boolean vissible;
    private HttpStatus httpStatus;

    LocationExceptionModel(String message, boolean vissible, HttpStatus httpStatus) {
        this.message = message;
        this.vissible = vissible;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public boolean isVissible() {
        return this.vissible;
    }

    @Override
    public HttpStatus getStatusCode() {
        return this.httpStatus;
    }
}
