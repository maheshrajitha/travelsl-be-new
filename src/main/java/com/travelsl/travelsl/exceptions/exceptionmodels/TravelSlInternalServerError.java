package com.travelsl.travelsl.exceptions.exceptionmodels;

import com.travelsl.travelsl.exceptions.TravelSlErrorHandler;
import org.springframework.http.HttpStatus;

public enum TravelSlInternalServerError implements TravelSlErrorHandler {
    NO_SUCH_ALGORITH_EXCEPTION("No Such Algorithem Error",false , HttpStatus.INTERNAL_SERVER_ERROR),
    MAP_BOX_ERROR("Map Box Error Occured",false,HttpStatus.INTERNAL_SERVER_ERROR),
    NO_COORDS("Empty Coordinates",true,HttpStatus.INTERNAL_SERVER_ERROR);
    ;
    private String message;
    private boolean vissible;
    private HttpStatus httpStatus;

    TravelSlInternalServerError(String message, boolean vissible, HttpStatus httpStatus) {
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
