package com.target.myretail;

import com.target.myretail.model.*;
import com.target.myretail.repository.ProductRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestOperations;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestProductController {

    @MockBean
    RestOperations restTemplate;
    @MockBean
    ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getProduct() throws Exception {
        ProductDetails pricingInfo = new ProductDetails();
        pricingInfo.setCurrencyCode("USD");
        pricingInfo.setId(13860428);
        pricingInfo.setPrice(BigDecimal.valueOf(12.39));

        Mockito.when(productRepository.findById(13860428)).thenReturn(Optional.of(pricingInfo));

        RedSkyProduct redSkyProduct = new RedSkyProduct();
        Product product = new Product();
        Item item = new Item();
        item.setBuyUrl("http://someURL");
        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("product Title");
        item.setProductDescription(productDescription);
        item.setUpc("1234567");
        product.setItem(item);
        redSkyProduct.setProduct(product);

        Mockito.when(restTemplate.exchange(Mockito.anyString(),Mockito.eq(HttpMethod.GET), Mockito.eq(null), Mockito.eq(RedSkyProduct.class), Mockito.eq(13860428))).
                thenReturn(new ResponseEntity<>(redSkyProduct,HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/product/13860428"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(13860428)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("product Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.upc",Matchers.is("1234567")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.buy_url",Matchers.is("http://someURL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.current_price.value",Matchers.is(12.39)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.current_price.currency_code",Matchers.is("USD")));


        Mockito.verify(productRepository).findById(13860428);
        Mockito.verify(restTemplate).exchange(Mockito.anyString(),Mockito.eq(HttpMethod.GET), Mockito.eq(null), Mockito.eq(RedSkyProduct.class), Mockito.eq(13860428));
    }


    @Test
    public void getProductPricingException() throws Exception {


        ProductDetails pricingInfo = new ProductDetails();
        pricingInfo.setCurrencyCode("USD");
        pricingInfo.setId(13860428);
        pricingInfo.setPrice(BigDecimal.valueOf(12.39));

        Mockito.when(productRepository.findById(13860428)).thenThrow(new DataAccessException("...."){});

        RedSkyProduct redSkyProduct = new RedSkyProduct();
        Product product = new Product();
        Item item = new Item();
        item.setBuyUrl("http://someURL");
        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("product Title");
        item.setProductDescription(productDescription);
        item.setUpc("1234567");
        product.setItem(item);
        redSkyProduct.setProduct(product);

        Mockito.when(restTemplate.exchange(Mockito.anyString(),Mockito.eq(HttpMethod.GET), Mockito.eq(null), Mockito.eq(RedSkyProduct.class), Mockito.eq(13860428))).
                thenReturn(new ResponseEntity<>(redSkyProduct,HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/product/13860428"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(13860428)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("product Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.upc",Matchers.is("1234567")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.buy_url",Matchers.is("http://someURL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_message",Matchers.is("Unable to retrieve pricing information")));


        Mockito.verify(productRepository).findById(13860428);
        Mockito.verify(restTemplate).exchange(Mockito.anyString(),Mockito.eq(HttpMethod.GET), Mockito.eq(null), Mockito.eq(RedSkyProduct.class), Mockito.eq(13860428));
    }

    @Test
    public void getProductRedSky404() throws Exception {


        Mockito.when(productRepository.findById(13860428)).thenThrow(new DataAccessException("..."){});

        Mockito.when(restTemplate.exchange(Mockito.anyString(),Mockito.eq(HttpMethod.GET), Mockito.eq(null), Mockito.eq(RedSkyProduct.class), Mockito.eq(13860428))).
                thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/product/13860428"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("NOT_FOUND")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_message", Matchers.is("Product 13860428 Not found")));


        Mockito.verify(productRepository,Mockito.times(0)).findById(13860428);
        Mockito.verify(restTemplate).exchange(Mockito.anyString(),Mockito.eq(HttpMethod.GET), Mockito.eq(null), Mockito.eq(RedSkyProduct.class), Mockito.eq(13860428));
    }

}
