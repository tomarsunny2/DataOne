/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.json.transformer.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 *
 * @author sgtomar
 */
@Data
public class FlattenRequestBean {
     @JsonProperty("fruit")
    private String fruit;

    @JsonProperty("animal")
    private String animal;
    
    @JsonProperty("city-list")
    private String cityList;
    
}
