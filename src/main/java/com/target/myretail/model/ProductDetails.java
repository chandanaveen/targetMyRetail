package com.target.myretail.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table(value = "product_details")
public class ProductDetails {

    @PrimaryKey
    private Integer id;
    @Column("product_price")
    private BigDecimal price;
    @Column("currency_code")
    private String currencyCode;
}
