package commonClasses.sharedUtils.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.builders.Credentials;
import commonClasses.sharedUtils.builders.Environment;

public class LocalTest {

    private static ThreadLocal < String > testName = new ThreadLocal < String > ();
    private static Environment environment;
    private static ThreadLocal < Credentials > credentials = new ThreadLocal < Credentials > ();

    public static String getTestName() {
        return testName.get();
    }

    public static void setTestName(String name) {
        testName.set(name);
    }

    public static Environment getEnvironment() {
        return environment;
    }

    public static void setEnvironment(Environment value) {
        environment = value;
    }

    public static Credentials getCredentials() {
        return credentials.get();
    }

    public static void setCredentials(Credentials value) {
        credentials.set(value);
    }

    public static void initializeSettings() throws Exception {
        try {
            File configFile = new File(TestUtils.getRelativePath() + File.separatorChar + "resources" + File.separatorChar + "config.properties");
            InputStream inputStream = new FileInputStream(configFile);
            Properties props = new Properties();

            props.load(inputStream);

            String appUrl = setValueIfSystemPropIsNull(props, "ApplicationUrl", "AppUrl");
            String newstronUN = setValueIfSystemPropIsNull(props, "NewstronEncryptedUserName", "");
            String newstronPWD = setValueIfSystemPropIsNull(props, "NewstronEncryptedPassword", "");
            String env = setValueIfSystemPropIsNull(props, "Environment", "Env");
            String browser = setValueIfSystemPropIsNull(props, "Browser", "Browser");
            Boolean isHeadless = Boolean.parseBoolean(setValueIfSystemPropIsNull(props, "IsHeadless", ""));
            String miraUserName = setValueIfSystemPropIsNull(props, "miraEncryptedUserName", "");
            String miraPassword = setValueIfSystemPropIsNull(props, "miraEncryptedPassword", "");
            String os = setValueIfSystemPropIsNull(props, "OS", "");

            Environment environment = new Environment(appUrl, env, browser, os, isHeadless);
            Credentials credentials = new Credentials(miraUserName, miraPassword, newstronUN, newstronPWD);
            setCredentials(credentials);
            setEnvironment(environment);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private static String setValueIfSystemPropIsNull(Properties props, String prop, String systemProp) {
        String value = null;
        String sysProp = null;
        try {
            if (!TestUtils.isNullOrBlank(systemProp)) {
                sysProp = System.getProperty(systemProp);
            }

            if (TestUtils.isNullOrBlank(sysProp)) {
                value = props.getProperty(prop, "");
            } else {
                value = sysProp;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return value;
    }

}