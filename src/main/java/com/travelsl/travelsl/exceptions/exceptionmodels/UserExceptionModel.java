package com.travelsl.travelsl.exceptions.exceptionmodels;

import com.travelsl.travelsl.exceptions.TravelSlErrorHandler;
import org.springframework.http.HttpStatus;

public enum UserExceptionModel implements TravelSlErrorHandler {
    USER_ALREADY_EXISTS("User Exists With This Email",true,HttpStatus.CONFLICT)
    ;

    private String message;

    UserExceptionModel(String message, boolean vissible, HttpStatus httpStatus) {
        this.message = message;
        this.vissible = vissible;
        this.httpStatus = httpStatus;
    }

    private boolean vissible;
    private HttpStatus httpStatus;


    @Override
    public String getCode() {
        return this.name();
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
