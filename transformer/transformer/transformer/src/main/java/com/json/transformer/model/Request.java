
package com.json.transformer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Sunny
 */
@Data
public class Request {

    @JsonProperty("fruit")
    private String fruit;

    @JsonProperty("animal")
    private String animal;
    
    @JsonProperty("city-list")
    private List<String> cityList;
    
   
    
}
