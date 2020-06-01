package com.travelsl.travelsl.exceptions;

import lombok.Setter;
import org.springframework.http.HttpStatus;

public class TravelSlErrorHandlerImpl implements TravelSlErrorHandler {
    @Setter
    private String code;
    @Setter
    private String message;
    @Setter
    private boolean vissible;
    @Setter
    private HttpStatus httpStatus;
    @Override
    public String getCode() {
        return this.code;
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
