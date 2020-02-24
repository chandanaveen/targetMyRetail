package com.target.myretail.controller;

import com.target.myretail.exception.MyRetailException;
import com.target.myretail.exception.MyRetailExceptionHandler;
import com.target.myretail.model.ProductDto;
import com.target.myretail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Product Controller, all the product related api's
 */
@RestController
public class ProductController extends MyRetailExceptionHandler {

    @Autowired
    ProductService productService;

    @GetMapping(value = "v1/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProductDto retriveProductDetailsById(@PathVariable Integer id) throws MyRetailException {
        return productService.retrieveProduct(id);
    }
}
