package com.products.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.products.model.BrandGroupingSerializer;
import com.products.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author sgtomar
 */
public class TestData {

    public static Product getProduct() {
        Product product = new Product();

        product.setBrand("Brand A");
        product.setName("Product A");
        product.setOnSale(Boolean.TRUE);
        product.setPrice(new BigDecimal(11.25));
        return product;
    }

    public static String getProductAsJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getProducts());
    }

    public static List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(getProduct());

        return productList;
    }

    public static List<Product> getSortedProductByBrand(List<Product> productList) {
        Map<String, List<Product>> groupedMap = productList.stream().sorted(
                (p1, p2) -> p1.getPrice().compareTo(p2.getPrice())).collect(Collectors.groupingBy(Product::getBrand));
        List<Product> resproductList = new ArrayList<Product>();
        for (String brandName : groupedMap.keySet()) {
            List<Product> products = groupedMap.get(brandName);
            for (Product product : products) {
                resproductList.add(product);
            }
        }
        return resproductList;

    }

    public static String getBrandGroupingSerializer() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new BrandGroupingSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(TestData.getProducts());
    }
}
