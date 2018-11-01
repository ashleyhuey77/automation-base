package common.base.helpers;

import java.util.List;
import org.openqa.selenium.WebElement;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.vobjects.TestElement;

public class ElementHelper {
	
	private WebElement webElement;

	public ElementHelper(TestElement element) throws TestException {
		webElement = SHelper.get().element().get(element);
	}
	
	public ElementHelper(List<WebElement> elementList, String elementText) {
		try {
        		webElement = elementList.stream()
        		          .filter(d -> d.getText().equalsIgnoreCase(elementText))
        		          .findFirst()
        		          .orElse(null);
		} catch (Exception e) {
			webElement = null;
		}
	}
	
	public WebElement get() {
		return webElement;
	}
}
