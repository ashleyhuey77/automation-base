package commonClasses.sharedUtils;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
 
public class LocalDriverFactory {
	
    public static WebDriver createInstance(String browserName) {
    	
    	WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {

        }
        if (browserName.toLowerCase().contains("internet")) {
            /*driver = new InternetExplorerDriver();
            return driver;*/
        }
        if (browserName.toLowerCase().contains("chrome")) {
        	ChromeDriverFactory cFac = new ChromeDriverFactory();
        	driver = cFac.driver.get();
        }
        if (browserName.toLowerCase().trim().equals("safari"))
        {
           /* driver = SafariDriverFactory.driver.get();
            LocalDriverManager.setFFWebDriver(driver);*/
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
