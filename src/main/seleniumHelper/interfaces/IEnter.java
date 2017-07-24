package seleniumHelper.interfaces;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public interface IEnter {
	
	public void textInto(String selectorString, String by, String text) throws Exception;
	
	public void textInto(WebElement element, String text) throws Exception;
	
	public void textInto(String selectorString, String by, Keys key) throws Exception;
	
	public void textInto(WebElement element, Keys key) throws Exception;
	
	public void clear(String selectorString, String by) throws Exception;
	
	public void clear(WebElement element) throws Exception;

}
