package seleniumHelper.interfaces;

import org.openqa.selenium.WebElement;
import seleniumHelper.enums.SelectType;

public interface IActions {
	
public void moveTo(String selectorString, String by) throws Exception;
	
	public void moveTo(WebElement element) throws Exception;
	
	public void mouseOver(String selectorString, String by) throws Exception;
	
	public void mouseOver(WebElement element) throws Exception;
	
	public void dragAndDrop(WebElement firstElement, WebElement secondElement, String firstElementSelectorString, String index, String stepWidth, String stepDelay, String dx, String dy) throws Exception;
	
	public void dragAndDrop(String firstElementSelectorString, String firstElementBy, String secondElementSelectorString, String secondElementBy, String stepWidth, String stepDelay, String dx, String dy) throws Exception;
	
	public void dragAndDrop(String dragable , String dragableby,  String dropable, String dropableby, int timeTowait) throws Exception;
	
	public void scrollTo(String selectorString, String by) throws Exception;
	
	public void scrollTo(WebElement element) throws Exception;
	
	public void selectFromDropDown(WebElement element, String value, SelectType selectType) throws Exception;
	
	public void selectFromDropDown(String selectorString, String by, String value, SelectType selectType) throws Exception;

}
