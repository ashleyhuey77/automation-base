package com.data.loki;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

    @JsonProperty("ref")
    public Local ref;

    @JsonProperty("dev")
    public Local dev;
}
