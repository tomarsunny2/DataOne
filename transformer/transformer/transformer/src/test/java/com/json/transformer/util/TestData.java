/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.json.transformer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.transformer.model.Request;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import net.minidev.json.JSONArray;

import org.assertj.core.util.Arrays;
import org.json.JSONObject;

/**
 *
 * @author sgtomar
 */
public class TestData {

    public static Request getRequest() {
        Request request = new Request();
        request.setAnimal("apple");
        request.setFruit("zebra");
        List<String> cityList = new ArrayList<>();
        cityList.add("sunnyvale");
        cityList.add("sanjose");
        request.setCityList(cityList);
        return request;
    }

    public static FlattenRequestBean getExpectedResponse() {
        FlattenRequestBean request = new FlattenRequestBean();
        request.setAnimal("apple");
        request.setFruit("zebra");
        List<String> cityList = new ArrayList<>();
        cityList.add("sunnyvale");
        cityList.add("sanjose");
        request.setCityList(cityList.stream().collect(Collectors.joining(",")));
        return request;
    }

    public static String getRequestAsJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(getRequest());
    }

    public static String getRequestAsJsonInAscOrder() throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new TreeMap<String, Object>();
        map = mapper.readValue(getRequestAsJson(), new TypeReference<TreeMap<String, Object>>() {
        });
        return mapper.writeValueAsString(map);
    }

    public static String getResponseAsJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(getExpectedResponse());
    }
    
}
