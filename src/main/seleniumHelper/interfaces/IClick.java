package seleniumHelper.interfaces;

import org.openqa.selenium.WebElement;

public interface IClick {

	public void on(String selectorString, String by) throws Exception;
	
	public void on(WebElement element) throws Exception;
	
	public void on(String selectorString, String by, String index) throws Exception;
	
	public void on(String selectorString, String by, int index) throws Exception;
}
