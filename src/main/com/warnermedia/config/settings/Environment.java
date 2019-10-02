package com.warnermedia.config.settings;

public class Environment {

    private static String applicationUrl;
    private static String env;
    private static String browser;
    private static String os;
    private static Boolean isHeadlessEnabled;
    private static String bureau;

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public String getEnvironment() {
        return env;
    }

    public String getBrowser() {
        return browser;
    }

    public Boolean isHeadlessEnabled() {
        return isHeadlessEnabled;
    }

    public String getOS() {
        return os;
    }
    
    public String getBureau() {
    	return bureau;
    }

    public Environment(String applicationUrl, String environment, String browser, String os, String bureau, Boolean isHeadless) {
        Environment.applicationUrl = applicationUrl;
        Environment.env = environment;
        Environment.browser = browser;
        Environment.isHeadlessEnabled = isHeadless;
        Environment.os = os;
        Environment.bureau = bureau;
    }

}
