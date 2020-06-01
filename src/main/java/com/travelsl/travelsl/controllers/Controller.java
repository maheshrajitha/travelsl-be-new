package com.travelsl.travelsl.controllers;

import com.travelsl.travelsl.exceptions.TravelSlErrorHandlerImpl;
import com.travelsl.travelsl.exceptions.TravelSlException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface Controller {

    @ExceptionHandler(TravelSlException.class)
    default ResponseEntity<?> exceptionHandler(TravelSlException travelSlException){
        TravelSlErrorHandlerImpl travelSlErrorHandler = new TravelSlErrorHandlerImpl();
        travelSlErrorHandler.setCode(travelSlException.getTravelSlErrorHandler().getCode());
        travelSlErrorHandler.setMessage(travelSlException.getTravelSlErrorHandler().getMessage());
        travelSlErrorHandler.setVissible(travelSlException.getTravelSlErrorHandler().isVissible());
        travelSlErrorHandler.setHttpStatus(travelSlException.getTravelSlErrorHandler().getStatusCode());
        return ResponseEntity.status(travelSlException.getTravelSlErrorHandler().getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(travelSlErrorHandler);
    }
}
