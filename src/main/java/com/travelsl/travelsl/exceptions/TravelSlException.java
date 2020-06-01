package com.travelsl.travelsl.exceptions;

public class TravelSlException extends RuntimeException {

    private final TravelSlErrorHandler travelSlErrorHandler;

    public TravelSlException(TravelSlErrorHandler travelSlErrorHandler){
        super(travelSlErrorHandler.getMessage());
        this.travelSlErrorHandler = travelSlErrorHandler;
    }
    public TravelSlErrorHandler getTravelSlErrorHandler() {
        return travelSlErrorHandler;
    }

}
