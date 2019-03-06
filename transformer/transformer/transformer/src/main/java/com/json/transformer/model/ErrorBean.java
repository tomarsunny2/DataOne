package com.json.transformer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/*
* Error Bean for showing error in JSON Format
 */
@Data
public class ErrorBean {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("user_message")
    private String userMessage;

}
