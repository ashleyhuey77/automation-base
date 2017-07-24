package seleniumHelper.interfaces;

import org.openqa.selenium.WebElement;

public interface IText {

	public String getFrom(String selectorString, String by, String...attribute) throws Exception;
	
	public String getFrom(WebElement element, String...attribute) throws Exception;
	
	public Boolean isDisplayed(String selectorString, String by, String expectedText, String...attribute) throws Exception;
	
	public Boolean isDisplayed(WebElement element, String expectedText, String...attribute) throws Exception;
	
}
