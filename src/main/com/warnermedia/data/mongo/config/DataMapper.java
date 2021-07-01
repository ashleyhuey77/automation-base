package com.warnermedia.data.mongo.config;

import com.warnermedia.data.mongo.models.*;

public class DataMapper {

    private static ThreadLocal<Person> person = new ThreadLocal<>();
    private static ThreadLocal<Video> video = new ThreadLocal<>();
    private static ThreadLocal<Asset> asset = new ThreadLocal<>();
    private static ThreadLocal<Global> global = new ThreadLocal<>();
    private static ThreadLocal<Local> local = new ThreadLocal<>();
    private static ThreadLocal<Data> data = new ThreadLocal<>();

    public static void setPerson(final Person value) {
        person.set(value);
    }

    public static Person person() {
        return person.get();
    }

    public static void setVideo(final Video value) {
        video.set(value);
    }

    public static Video video() {
        return video.get();
    }

    public static void setAsset(final Asset value) {
        asset.set(value);
    }

    public static Asset asset() {
        return asset.get();
    }

    public static void setGlobal(final Global value) {
        global.set(value);
    }

    public static Global global() {
        return global.get();
    }

    public static void setLocal(final Local value) {
        local.set(value);
    }

    public static Local local() {
        return local.get();
    }

    public static void setData(final Data value) {
        data.set(value);
    }

    public static Data data() {
        return data.get();
    }
}
