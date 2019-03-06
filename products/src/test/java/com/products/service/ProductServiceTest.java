package com.products.service;


import com.products.model.Product;
import com.products.repository.ProductRepository;
import com.products.utils.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author sgtomar
 */
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {

        @Bean
        public ProductService productService() {
            return new ProductServiceImpl();
        }
    }

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void whenFindAll_thenGetAllProductList() {
        //Setup
        
        List<Product> productList = TestData.getProducts();
        Mockito.when(productRepository.findAll())
                .thenReturn(productList);
        //When
        List<Product> expectedProductList = productService.findAll();
        //Then
        assertThat(expectedProductList)
                .isEqualTo(productList);
    }
    
    @Test
    public void whenfindProductByBrand_thenGetAllProductBasedOnBrand() {
        //Setup
        
        List<Product> productList = TestData.getSortedProductByBrand(TestData.getProducts());
        Mockito.when(productRepository.findProductByBrand())
                .thenReturn(productList);
        //When
        List<Product> expectedProductList = productService.findProductByBrand();
        //Then
        assertThat(expectedProductList)
                .isEqualTo(productList);
    }

    
}
