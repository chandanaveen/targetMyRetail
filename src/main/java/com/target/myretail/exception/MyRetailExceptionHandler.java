package com.target.myretail.exception;

import com.target.myretail.model.MyRetailErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * MyRetailExceptionHandler to handle all the exception responses
 */
public class MyRetailExceptionHandler {

    @ExceptionHandler(MyRetailException.class)
    public ResponseEntity<MyRetailErrorResponse> handleMyRetailException(MyRetailException ex) {
        MyRetailErrorResponse myRetailErrorResponse = new MyRetailErrorResponse();
        myRetailErrorResponse.setErrorMessage(ex.getMessage());
        myRetailErrorResponse.setStatus(ex.getStatus());
        return new ResponseEntity<>(myRetailErrorResponse, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyRetailErrorResponse> handleGenericException(Exception ex) {
        MyRetailErrorResponse errorResponse = new MyRetailErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setErrorMessage("Internal Error :" + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
