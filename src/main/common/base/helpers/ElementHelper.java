package common.base.helpers;

import java.util.List;
import org.openqa.selenium.WebElement;
import common.utils.managers.LocalValidation;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.enums.Condition;
import shelper.vobjects.TestElement;

public class ElementHelper {

	private WebElement webElement;

	public ElementHelper(ElementBuilder builder) throws TestException {
		if (builder.elementList != null
				&& !builder.elementList.isEmpty()) {
			if (builder.condition == Condition.CONTAIN) {
				webElement = builder.elementList.stream().filter(d -> d.getText().toLowerCase().contains(builder.elementText.toLowerCase())).findFirst()
						.orElse(null);
			} else if (builder.condition == Condition.EQUAL) {
				webElement = builder.elementList.stream().filter(d -> d.getText().equalsIgnoreCase(builder.elementText)).findFirst()
						.orElse(null);
			}
			
		} else if (builder.element != null) {
			webElement = SHelper.get().element().get(builder.element);
		} else { 
			throw LocalValidation.getValidations().assertionFailed("Please provide either an element or a list of elements.");
		}
	}

	public WebElement get() {
		return webElement;
	}
	
	public static class ElementBuilder {
		
		//required
		private List<WebElement> elementList;
		private TestElement element;
		
		//optional
		private String elementText;
		private Condition condition;
		
		public ElementBuilder(List<WebElement> elementList) {
			this.elementList = elementList;
			if (condition == null) {
				condition = Condition.EQUAL;
			}
		}
		
		public ElementBuilder(TestElement element) {
			this.element = element;
		}
		
		public ElementBuilder text(String text) {
			this.elementText = text;
			return this;
		}
		
		public ElementBuilder that(Condition condition) {
			this.condition = condition;
			return this;
		}
	}
}
