package com.travelsl.travelsl.exceptions.exceptionmodels;

import com.travelsl.travelsl.exceptions.TravelSlErrorHandler;
import org.springframework.http.HttpStatus;

public enum AuthExceptionModel implements TravelSlErrorHandler {
    PASSWORD_NOT_MATCHED("Password Not Match",true ,HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("There Is No User",true , HttpStatus.NOT_FOUND),
    TOKEN_NOT_VALIED("Access Token Not Valied",true,HttpStatus.UNAUTHORIZED),
    EMPTY_TOKEN("Token Empty",true,HttpStatus.UNAUTHORIZED),
    NO_ACCESS("User Has No Access",false , HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("Unauthorized User",true,HttpStatus.UNAUTHORIZED)
    ;

    private String message;
    private boolean vissible;
    private HttpStatus httpStatus;

    AuthExceptionModel(String message, boolean vissible, HttpStatus httpStatus) {
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
