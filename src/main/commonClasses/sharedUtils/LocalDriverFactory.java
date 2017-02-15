package commonClasses.sharedUtils;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

//import com.gargoylesoftware.htmlunit.BrowserVersion;

import commonClasses.sharedUtils.TestUtils;
 
public class LocalDriverFactory {
    public static WebDriver createInstance(String browserName) {
    	
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
        	System.setProperty("webdriver.gecko.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/geckodriver");
            driver = new FirefoxDriver();
            return driver;
        }
        if (browserName.toLowerCase().contains("internet")) {
            driver = new InternetExplorerDriver();
            return driver;
        }
        if (browserName.toLowerCase().contains("chrome")) {
        	System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        	System.setProperty("java.awt.headless", "false");
            driver = new ChromeDriver();
            return driver;
        }
        if (browserName.toLowerCase().trim().equals("safari"))
        {
            SafariOptions safariOptions = new SafariOptions();
            driver = new SafariDriver(safariOptions);
        }
        if (browserName.toLowerCase().trim().contains("phantom"))
        {
        	/*new DesiredCapabilities();
			Capabilities caps = DesiredCapabilities.phantomjs();
            ((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--web-security=false", "--ignore-ssl-errors=true", "--ssl-protocol=TLSv1", 
                    "--webdriver-loglevel=DEBUG", "--load-images=true"});
            ((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent", "My User Agent - Chrome");
            ((DesiredCapabilities) caps).setCapability("phantomjs.page.settings.userAgent", "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
            ((DesiredCapabilities) caps).setJavascriptEnabled(true);
            ((DesiredCapabilities) caps).setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    TestUtils.getRelativePath() + "/externalLibraries/browsers/phantomjs"
                );*/
        
        	//((JavascriptExecutor)browser).executeScript("var script = document.createElement('script'); script.src = 'https://raw.githubusercontent.com/zloirock/core-js/master/client/core.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
        	//System.setProperty("phantomjs.binary.path", TestUtils.getRelativePath() + "/externalLibraries/browsers/phantomjs");
        	//driver = new PhantomJSDriver(caps);
        	
        	//driver2 = new HtmlUnitDriver(BrowserVersion.CHROME);
        	//driver2.setJavascriptEnabled(true);
        	java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        }
        return driver;
    }
}
