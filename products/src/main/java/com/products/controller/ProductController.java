
package com.products.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.products.model.BrandGroupingSerializer;
import com.products.model.Product;
import com.products.service.ProductService;
import io.swagger.annotations.Api;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sgtomar
 */
@RestController
@RequestMapping("/")
@Api(value="product", description="Operations pertaining to products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    /**
     * Method to get All Products List
     */
    @RequestMapping(value = "products", method = RequestMethod.GET)
    public List<Product> getProducts() {
        return productService.findAll();
    }

    /**
     * Method to get All Product grouped by Brand
     */
    @RequestMapping(value = "brands", method = RequestMethod.GET, produces = "application/json")
    public String getBrands() throws JsonProcessingException {
        List<Product> productList = productService.findProductByBrand();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new BrandGroupingSerializer()); // Used Serializer
        mapper.registerModule(module);
        return mapper.writeValueAsString(productList);

    }

}
