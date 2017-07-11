package seleniumHelper.methods;

import java.util.List;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IClick;

public class Click extends Commands implements IClick {

	@Override
	public void on(String selectorString, String by) {
        try
        {
        	getElement(selectorString, by);
            getElement(selectorString, by).click();
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
        	throw ex;
        }
	}

	@Override
	public void on(WebElement element) {
        try
        {
            element.click();
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
	}

	@Override
	public void on(String selectorString, String by, String index) {
        try
        {
        	List<WebElement> element =  getElements(selectorString, by);
            element.get(Integer.parseInt(index)).click();
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
        	throw ex;
        }
	}
	
	@Override
	public void on(String selectorString, String by, int index) {
        try
        {
        	List<WebElement> element =  getElements(selectorString, by);
            element.get(index).click();
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
        	throw ex;
        }
	}

}
