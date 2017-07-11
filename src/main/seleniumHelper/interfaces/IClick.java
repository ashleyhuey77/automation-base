package seleniumHelper.interfaces;

import org.openqa.selenium.WebElement;

public interface IClick {

	public void on(String selectorString, String by);
	
	public void on(WebElement element) throws Exception;
	
	public void on(String selectorString, String by, String index);
	
	public void on(String selectorString, String by, int index);
}
