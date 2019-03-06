
package com.products.service;
import java.util.*;
import com.products.model.Product;


/**
 *
 * @author sgtomar
 */
public interface ProductService {
    List<Product> findAll();
    List<Product> findProductByBrand();
}
