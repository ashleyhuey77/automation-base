package com.warnermedia.data.mongo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {

    @JsonProperty("url")
    private List<String> url;

    @JsonProperty("file_path")
    private List<String> filePath;

    public String url() {
        Collections.shuffle(url);
        return url.get(0);
    }

    public String filePath() {
        Collections.shuffle(filePath);
        return filePath.get(0);
    }
}
