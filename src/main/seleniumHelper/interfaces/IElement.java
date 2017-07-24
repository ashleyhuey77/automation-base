package seleniumHelper.interfaces;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface IElement {
	
	public WebElement get(String selectorString, String by) throws Exception;

	public List<WebElement> getListOf(String selectorString, String by) throws Exception;
	
	public Boolean isDisplayed(String selectorString, String by, int i) throws Exception;
	
	public Boolean isDisplayed(WebElement element, int i) throws Exception;
	
	public WebElement find(WebElement firstElement, String selectorString, String by) throws Exception;
	
	public WebElement find(String firstESelectorString, String firstEBy, String by, String selectorString) throws Exception;
	
	public List<WebElement> findListOf(WebElement firstElement, String selectorString, String by) throws Exception;
	
	public List<WebElement> findListOf(String firstESelectorString, String firstEBy, String by, String selectorString) throws Exception;
	
	public Boolean isAttributePresent(WebElement element, String attribute) throws Exception;
	
	public Boolean isAttributePresent(String selectorString, String by, String attribute) throws Exception;
	
	public Boolean isEnabled(String selectorString, String by) throws Exception;
	
	public Boolean isEnabled(WebElement element) throws Exception;
}
