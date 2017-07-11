package seleniumHelper.interfaces;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public interface IEnter {
	
	public void textInto(String selectorString, String by, String text);
	
	public void textInto(WebElement element, String text);
	
	public void textInto(String selectorString, String by, Keys key);
	
	public void textInto(WebElement element, Keys key);
	
	public void clear(String selectorString, String by);
	
	public void clear(WebElement element);

}
