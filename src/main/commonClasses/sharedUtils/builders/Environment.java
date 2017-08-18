package commonClasses.sharedUtils.builders;

public class Environment {

    private static String applicationUrl;
    private static String environment;
    private static String browser;
    private static String os;
    private static Boolean isHeadlessEnabled;

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public String getEnvironment() {
        return environment;
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

    public Environment(String applicationUrl, String environment, String browser, String os, Boolean isHeadless) {
        Environment.applicationUrl = applicationUrl;
        Environment.environment = environment;
        Environment.browser = browser;
        Environment.isHeadlessEnabled = isHeadless;
        Environment.os = os;
    }

}