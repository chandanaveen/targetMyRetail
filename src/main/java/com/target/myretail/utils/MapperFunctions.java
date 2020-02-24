package com.target.myretail.utils;

import com.target.myretail.model.*;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class MapperFunctions {

    /**
     * Function to convert RedSkyProduct (Object fetched form RedSky portal) object to ProductDto
     */
    public Function<RedSkyProduct, ProductDto> redskyProductToProductDtoFunction = (redskyProduct) -> {
        ProductDto dto = new ProductDto();

        Optional.of(redskyProduct)
                .map(RedSkyProduct::getProduct)
                .map(Product::getItem).ifPresent(item ->{
                dto.setBuyUrl(item.getBuyUrl());
                dto.setUpc(item.getUpc());
                if(item.getProductDescription() !=null){
                    dto.setName(item.getProductDescription().getTitle());
                }
                });
        return dto;
    };

    /**
     * Function to convert ProductDetails(Object fetched form cassandra) object to CurrentPriceDto
     */
    public Function<ProductDetails, CurrentPriceDto> productDetailsToCurrentPriceFunction = (productDetails) -> {

        CurrentPriceDto currentPriceDto = new CurrentPriceDto();

        Optional.of(productDetails)
                .map(ProductDetails::getPrice)
                .ifPresent(price -> currentPriceDto.setValue(price));

        Optional.of(productDetails)
                .map(ProductDetails::getCurrencyCode)
                .ifPresent(currencyCode -> currentPriceDto.setCurrencyCode(currencyCode));

        return currentPriceDto;
    };
}
