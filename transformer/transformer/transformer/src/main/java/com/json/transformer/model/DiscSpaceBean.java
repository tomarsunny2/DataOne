package com.json.transformer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author sgtomar
 */
@Data
@AllArgsConstructor
public class DiscSpaceBean {

    @JsonProperty("discname")
    private String discname;
    @JsonProperty("availbytes")
    private String availbytes;
}
