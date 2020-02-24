package com.target.myretail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties (ignoreUnknown = true)
@Data
public class Item {
    @JsonProperty(value = "product_description")
    private ProductDescription productDescription;
    private String upc;
    @JsonProperty(value= "buy_url")
    private String buyUrl;
}
