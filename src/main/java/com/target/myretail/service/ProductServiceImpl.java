package com.target.myretail.service;

import com.target.myretail.exception.MyRetailException;
import com.target.myretail.exception.ProductNotFoundException;
import com.target.myretail.model.CurrentPriceDto;
import com.target.myretail.model.ProductDetails;
import com.target.myretail.model.ProductDto;
import com.target.myretail.model.RedSkyProduct;
import com.target.myretail.repository.ProductRepository;
import com.target.myretail.utils.MapperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    RestOperations restOperations;
    @Autowired
    MapperFunctions mapperFunctions;

    @Value("${redsky.product.get}")
    String getUrl;

    @Override
    public ProductDto retrieveProduct(Integer productId) throws MyRetailException {
        RedSkyProduct redskyProduct = retrieveProductFromRedSky(productId);
        ProductDto productDto = mapperFunctions.redskyProductToProductDtoFunction.apply(redskyProduct);
        productDto.setId(productId);
        try {
            Optional<ProductDetails> productDetails = productRepository.findById(productId);
            productDetails.ifPresent(product -> {
                CurrentPriceDto currentPriceDto = mapperFunctions.productDetailsToCurrentPriceFunction.apply(product);
                productDto.setCurrentPriceDto(currentPriceDto);
            });
        }catch(DataAccessException exp){
            productDto.setErrorMessage("Unable to retrieve pricing information");
        }
        return productDto;
    }

    /**
     * retrieve Product information from redsky.target.com,
     * @param productId
     * @return RedSkyProduct
     * @throws MyRetailException - ProductNotFound if product is not found
     */
    private RedSkyProduct retrieveProductFromRedSky(Integer productId) throws MyRetailException {

        ResponseEntity<RedSkyProduct> responseEntity = restOperations.exchange(getUrl, HttpMethod.GET, null, RedSkyProduct.class, productId);

        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ProductNotFoundException("Product " + productId + " Not found", HttpStatus.NOT_FOUND);
        } else if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new MyRetailException("Bad Request", responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }


}
