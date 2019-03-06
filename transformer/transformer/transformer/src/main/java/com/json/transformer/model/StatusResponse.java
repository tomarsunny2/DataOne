package com.json.transformer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Sunny
 */
@Data
public class StatusResponse {

    @JsonProperty("mem-used-pct")
    private Long mem_used_pct;

    @JsonProperty("disc-space-avail")
    private List<DiscSpaceBean> disc_space_avail;

    @JsonProperty("cpu-used-pct")
    private Double cpu_used_pct;

}
