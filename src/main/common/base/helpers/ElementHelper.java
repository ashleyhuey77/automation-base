package common.base.helpers;

import java.util.List;
import org.openqa.selenium.WebElement;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class ElementHelper {
	
	private WebElement element;

	public ElementHelper(Locator locator, By by) throws TestException {
		element = SHelper.get().element().get(locator, by);
	}
	
	public ElementHelper(List<WebElement> elementList, String elementText) {
		try {
        		element = elementList.stream()
        		          .filter(d -> d.getText().equalsIgnoreCase(elementText))
        		          .findFirst()
        		          .orElse(null);
		} catch (Exception e) {
			element = null;
		}
	}
	
	public WebElement get() {
		return element;
	}
}
