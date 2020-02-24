package com.target.myretail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Pojo for holding RedSkyProduct information
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedSkyProduct {

    private Product product;
}
