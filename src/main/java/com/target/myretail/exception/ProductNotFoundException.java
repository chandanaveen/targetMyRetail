package com.target.myretail.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception class for Product Not Found Exception
 */
@Getter
public class ProductNotFoundException extends MyRetailException {

    public ProductNotFoundException(String errorMessage, HttpStatus status) {
        super(errorMessage, status);
    }

}
