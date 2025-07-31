package org.example.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Days {
    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("tempmax")
    private int tempmax;

    @JsonProperty("tempmin")
    private int tempmin;

    @JsonProperty("conditions")
    private String conditions;
}
