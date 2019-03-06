package com.products.controller;

import com.products.service.ProductService;
import com.products.utils.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author sgtomar
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void givenProduct_whenGetProducts_thenReturnProductJson()
            throws Exception {

        String json = TestData.getProductAsJson();
        given(productService.findAll()).willReturn(TestData.getProducts());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/products").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        JSONAssert.assertEquals(json, result.getResponse()
                .getContentAsString(), false);
    }
    
    @Test
    public void givenProducts_whenGetBrands_thenReturnProductBrandWiseJson()
            throws Exception {

        String json = TestData.getBrandGroupingSerializer();
        given(productService.findProductByBrand()).willReturn(TestData.getProducts());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/brands").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(json, result.getResponse()
                .getContentAsString(),false);
    }
}
