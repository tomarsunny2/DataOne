package com.json.transformer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.json.transformer.model.FlattenSerializer;
import com.json.transformer.model.Request;
import com.json.transformer.model.SortingSerializer;
import com.json.transformer.model.StatusResponse;
import com.json.transformer.util.TransformerUtil;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sunny
 */
@RestController
@RequestMapping("/")
@Api(value="Transformer", description="JSON transformer service")
public class TransformerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformerController.class);

    /**
     * Method to flatten JSON data
     */
    @RequestMapping(value = "flatten", method = RequestMethod.POST, produces = "application/json")
    public String flattenJson(@RequestBody Request request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(new FlattenSerializer(Request.class));
        mapper.registerModule(module);

        return mapper.writeValueAsString(request);
    }

    /**
     * Method to give status of memory and CPU usages
     */
    @RequestMapping(value = "status", method = RequestMethod.GET)
    public StatusResponse getApplicationByID() throws Exception {
        return TransformerUtil.getSystemInfo();
    }

    /**
     * Method to sort JSON Payload in Alphabetical order
     * 
     */
    @RequestMapping(value = "alpha", method = RequestMethod.PUT, produces = "application/json")
    public String sortByAlphaOrder(@RequestBody Request request) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new SortingSerializer(Request.class));
        mapper.registerModule(module);
        return mapper.writeValueAsString(request);
    }
}
