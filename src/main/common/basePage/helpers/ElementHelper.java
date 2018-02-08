package common.basePage.helpers;

import java.util.List;
import org.openqa.selenium.WebElement;
import common.utils.managers.SHelper;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class ElementHelper {
	
	private WebElement element;

	public ElementHelper(Locator locator, By by) throws Exception {
		element = SHelper.get().element().get(locator, by);
	}
	
	public ElementHelper(List<WebElement> elementList, String elementText) throws Exception {
		try {
        		element = elementList.stream()
        		          .filter(d -> d.getText().contentEquals(elementText))
        		          .findFirst()
        		          .get();
		} catch (Exception e) {
			element = null;
		}
	}
	
	public WebElement get() {
		return element;
	}
}
