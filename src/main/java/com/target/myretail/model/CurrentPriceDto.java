package com.target.myretail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPriceDto {
    private BigDecimal value;
    private String currencyCode;
}
