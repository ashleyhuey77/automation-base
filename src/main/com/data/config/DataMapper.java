package com.data.config;

import com.data.loki.*;

public class DataMapper {

    private static Person person;
    private static Video video;
    private static Asset asset;
    private static Global global;
    private static Local local;
    private static Data data;

    public static void setPerson(final Person value) {
        person = value;
    }

    public static Person person() {
        return person;
    }

    public static void setVideo(final Video value) {
        video = value;
    }

    public static Video video() {
        return video;
    }

    public static void setAsset(final Asset value) {
        asset = value;
    }

    public static Asset asset() {
        return asset;
    }

    public static void setGlobal(final Global value) {
        global = value;
    }

    public static Global global() {
        return global;
    }

    public static void setLocal(final Local value) {
        local = value;
    }

    public static Local local() {
        return local;
    }

    public static void setData(final Data value) {
        data = value;
    }

    public static Data data() {
        return data;
    }
}
