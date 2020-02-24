package com.target.myretail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProductDto - Dto as a response for get on product by ID
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private String upc;
    private String buyUrl;
    @JsonProperty(value = "current_price")
    private CurrentPriceDto currentPriceDto;
    private String errorMessage;
}
