package com.products.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author sgtomar
 */
public class BrandGroupingSerializer extends StdSerializer<List<Product>> {

    private static List<Product> product = new ArrayList<Product>();
    private static final String ON_SALE = "ON SALE";
    public BrandGroupingSerializer(Class<List<Product>> t) {
        super(t);
    }

    @SuppressWarnings("unchecked")
    public BrandGroupingSerializer() {
        this((Class<List<Product>>) product.getClass());

    }

    @Override
    public void serialize(List<Product> request, JsonGenerator gen, SerializerProvider sp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Product>> groupedMap = request.stream().collect(Collectors.groupingBy(Product::getBrand));
        Map<String, List<Brand>> resultMap = new HashMap<String, List<Brand>>();
        for (String brandName : groupedMap.keySet()) {
            List<Brand> groupedByBrand = new ArrayList<Brand>();
            List<Product> productList = groupedMap.get(brandName);
            for (Product product : productList) {
                Brand brand = new Brand();
                brand.setId(product.getId());
                brand.setName(product.getName());
                brand.setPrice(product.getPrice());
                if (product.getOnSale()) {
                    brand.setOnsale(ON_SALE);
                }
                groupedByBrand.add(brand);
            }
            resultMap.put(brandName, groupedByBrand);
        }
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap);
        gen.writeRaw(json);
    }
    

}
