package seleniumHelper.interfaces;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface IElement {
	
	public WebElement get(String selectorString, String by);

	public List<WebElement> getListOf(String selectorString, String by);
	
	public Boolean isDisplayed(String selectorString, String by, int i);
	
	public Boolean isDisplayed(WebElement element, int i);
	
	public WebElement find(WebElement firstElement, String selectorString, String by);
	
	public WebElement find(String firstESelectorString, String firstEBy, String by, String selectorString);
	
	public List<WebElement> findListOf(WebElement firstElement, String selectorString, String by);
	
	public List<WebElement> findListOf(String firstESelectorString, String firstEBy, String by, String selectorString);
	
	public Boolean isAttributePresent(WebElement element, String attribute);
	
	public Boolean isAttributePresent(String selectorString, String by, String attribute);
	
	public Boolean isEnabled(String selectorString, String by);
	
	public Boolean isEnabled(WebElement element);
}
