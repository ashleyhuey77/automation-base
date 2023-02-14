package com.warnermedia.utils.devtools.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Exclude {

    @JsonProperty("exceptions")
    public List<String> exceptions;

    public Exclude get(final String file) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final Exclude data = mapper.readValue(new File(file), Exclude.class);
        return data;
    }


}
