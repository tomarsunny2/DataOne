package com.json.transformer.controller;

import com.json.transformer.util.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
@WebMvcTest(TransformerController.class)
public class TransformerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFlattenAPI()
            throws Exception {

        String json = TestData.getRequestAsJson();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/flatten").accept(
                        MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(TestData.getResponseAsJson(), result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testAlphaAPI() throws Exception {

        String json = TestData.getRequestAsJson();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/alpha").accept(
                        MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(TestData.getRequestAsJsonInAscOrder(), result.getResponse()
                .getContentAsString(), false);
    }
   
}
