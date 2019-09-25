package common.utils.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import common.utils.TestUtils;
import common.utils.builders.Environment;
import log.TestException;

public class LocalTest {
	
	private LocalTest() {
		
	}

    private static ThreadLocal < String > testName = new ThreadLocal <> ();
    private static ThreadLocal<Boolean> isLoggingEnabled = new ThreadLocal<>();
    private static ThreadLocal<Environment> environment = new ThreadLocal<>();

    public static String getTestName() {
        return testName.get();
    }

    public static void setTestName(String name) {
        testName.set(name);
    }
    
    public static Boolean getIsLoggingEnabled() {
    	return isLoggingEnabled.get();
    }
    
    public static void setIsLoggingEnabled(Boolean value) {
    	isLoggingEnabled.set(value);
    }

    public static Environment getEnvironment() {
        return environment.get();
    }

    public static void setEnvironment(Environment value) {
        environment.set(value);
    }

    public static void initializeSettings() throws TestException {
    	try {
            File configFile = new File(TestUtils.getRelativePath() + File.separatorChar + "resources" + File.separatorChar + "config.properties");
            InputStream inputStream = new FileInputStream(configFile);
            Properties props = new Properties();

            props.load(inputStream);

            String appUrl = setValueIfSystemPropIsNull(props, "ApplicationUrl", "appUrl");
            Boolean isLoggingEnabled = Boolean.parseBoolean(setValueIfSystemPropIsNull(props, "IsLoggingEnabled", "isLoggingEnabled"));
            String env = setValueIfSystemPropIsNull(props, "Environment", "env");
            String bureau = setValueIfSystemPropIsNull(props, "Bureau", "bureau");
            String browser = setValueIfSystemPropIsNull(props, "Browser", "browser");
            Boolean isHeadless = Boolean.parseBoolean(setValueIfSystemPropIsNull(props, "IsHeadless", "isHeadless"));
            String os = setValueIfSystemPropIsNull(props, "OS", "oS");

            Environment environment = new Environment(appUrl, env, browser, os, bureau, isHeadless);
            setEnvironment(environment);
            setIsLoggingEnabled(isLoggingEnabled);
    	} catch (Exception e) {
    		
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
