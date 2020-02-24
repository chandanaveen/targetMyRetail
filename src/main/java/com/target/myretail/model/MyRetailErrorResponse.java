package com.target.myretail.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Pojo to hold Error Details
 */
@Data
public class MyRetailErrorResponse {

    private HttpStatus status;
    private String errorMessage;

}
