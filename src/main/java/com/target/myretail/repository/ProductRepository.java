package com.target.myretail.repository;

import com.target.myretail.model.ProductDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<ProductDetails, Integer> {

}
