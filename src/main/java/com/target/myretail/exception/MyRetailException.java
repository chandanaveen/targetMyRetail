package com.target.myretail.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Generic Exception class
 */
@Getter
public class MyRetailException extends Exception {

    private String errorMessage;
    private HttpStatus status;

    public MyRetailException(String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
