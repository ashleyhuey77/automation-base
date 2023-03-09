package com.data.loki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {

    @JsonProperty("source")
    private List<String> source;

    @JsonProperty("type")
    private List<String> type;

    @JsonProperty("for_program")
    private List<String> forProgram;

    @JsonProperty("native_aspect_ratio")
    private List<String> nar;

    @JsonProperty("reporter")
    private List<String> reporter;

    @JsonProperty("restrictions")
    private List<String> restrictions;

    @JsonProperty("alerts")
    private List<String> alerts;

    @JsonProperty("short_text")
    private List<String> shortText;

    @JsonProperty("division")
    private List<String> division;

    @JsonProperty("country")
    private List<String> country;

    @JsonProperty("country_abbr")
    private List<String> countryAbbr;

    @JsonProperty("state")
    private List<String> state;

    @JsonProperty("state_abbr")
    private List<String> stateAbbr;

    @JsonProperty("city")
    private List<String> city;

    @JsonProperty("county")
    private List<String> county;

    public String source() {
        Collections.shuffle(source);
        return source.get(0);
    }

    public String type() {
        Collections.shuffle(type);
        return type.get(0);
    }

    public String forProgram() {
        Collections.shuffle(forProgram);
        return forProgram.get(0);
    }

    public String nativeAspectRatio() {
        Collections.shuffle(nar);
        return nar.get(0);
    }

    public String reporter() {
        Collections.shuffle(reporter);
        return reporter.get(0);
    }

    public String restrictions() {
        Collections.shuffle(restrictions);
        return restrictions.get(0);
    }

    public String alerts() {
        Collections.shuffle(alerts);
        return alerts.get(0);
    }

    public String shortText() {
        Collections.shuffle(shortText);
        return shortText.get(0);
    }

    public String longText() {
        Random rand = new Random();
        int randomNum = rand.nextInt((20 - 2) + 1) + 2;
        String longString = null;
        for (int i=0; i < randomNum; i++) {
            Collections.shuffle(shortText);
            if (longString == null) {
                longString = shortText.get(0);
            } else {
                longString = longString + " " + shortText.get(0);
            }
        }
        return longString;
    }

    public String division() {
        Collections.shuffle(division);
        return division.get(0);
    }

    public String country() {
        Collections.shuffle(country);
        return country.get(0);
    }

    public String countryAbbr() {
        Collections.shuffle(countryAbbr);
        return countryAbbr.get(0);
    }

    public String state() {
        Collections.shuffle(state);
        return state.get(0);
    }

    public String stateAbbr() {
        Collections.shuffle(stateAbbr);
        return stateAbbr.get(0);
    }

    public String city() {
        Collections.shuffle(city);
        return city.get(0);
    }

    public String county() {
        Collections.shuffle(county);
        return county.get(0);
    }
}
