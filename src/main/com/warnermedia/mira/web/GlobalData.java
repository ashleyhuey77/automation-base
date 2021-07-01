package com.warnermedia.mira.web;

public class GlobalData {
    private static ThreadLocal<String> recordId = new ThreadLocal<String>();

    public static String getRecordId() {
        return recordId.get();
    }

    public static void setRecordId(String value) {
        recordId.set(value);
    }
}
