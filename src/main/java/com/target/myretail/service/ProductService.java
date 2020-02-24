package com.target.myretail.service;

import com.target.myretail.exception.MyRetailException;
import com.target.myretail.model.ProductDto;

public interface ProductService {
    /**
     *  Get the Product by productId, The Service fetches price info from cassandra and Other details from RedSky Api
     * @param productId
     * @return - Object of productDto
     * @throws MyRetailException
     */
    ProductDto retrieveProduct(Integer productId) throws MyRetailException;
}
