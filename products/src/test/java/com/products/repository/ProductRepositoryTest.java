package com.products.repository;

import com.products.model.Product;
import com.products.utils.TestData;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author sgtomar
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void whenGetByProductID_thenReturnProductObject() {

        // Given
        Product product = TestData.getProduct();
        entityManager.persist(product);

        //When
        Optional<Product> productDB = productRepository.findById(product.getId());

        //Then
        assertThat(productDB.get().getId())
                .isEqualTo(product.getId());

        assertThat(productDB.get().getBrand())
                .isEqualTo(product.getBrand());

        assertThat(productDB.get().getName())
                .isEqualTo(product.getName());

        assertThat(productDB.get().getPrice())
                .isEqualTo(product.getPrice());

        assertThat(productDB.get().getOnSale())
                .isEqualTo(product.getOnSale());
    }

    @Test
    public void whenGetProducts_thenReturnListOfProductsObject() {

        // Given
        List<Product> productList = new ArrayList<Product>();
        Product product = TestData.getProduct();
        entityManager.persist(product);
        productList.add(product);
        //When
        List<Product> productDB = (List<Product>) productRepository.findAll();

        //Then
        assertEquals(productList, productDB);
    }

    @Test
    public void whenGetProductsByBrand_thenReturnListOfProductsObject() {

        // Given
        List<Product> productList = new ArrayList<Product>();
        Product product = TestData.getProduct();
        entityManager.persist(product);
        productList.add(product);
        List<Product> list = TestData.getSortedProductByBrand(productList);

        //When
        List<Product> productDB = (List<Product>) productRepository.findProductByBrand();

        //Then
        assertEquals(list, productDB);
    }

}
