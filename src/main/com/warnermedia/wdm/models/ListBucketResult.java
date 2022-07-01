package com.warnermedia.wdm.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListBucketResult {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Contents")
    private List<Contents> contents;

    public List<Contents> getContents() {
        return contents;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Contents {

        @JsonProperty("Key")
        private String key;

        public String getKey() {
            return key;
        }
    }
}
