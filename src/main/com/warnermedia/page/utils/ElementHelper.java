package com.warnermedia.page.utils;

import java.util.List;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.wait.Condition;

public class ElementHelper {

	private WebElement webElement;
	
	/**
	 * <p> A shared generic helper used to select an
	 * element from a list of elements (via text) on the page.
	 * </p>
	 * <p> This helper using the Java stream functionality to locate
	 * a specific element in a list of elements based on the text 
	 * provided. If no element is found with the specified text,
	 * the element returns null.
	 * <pre>
	 * Ex:
	 * List<WebElement> elements = SHelper.get().element().getListOf(Generic.ELEMENT.element()); 
	 * {@code new ElementHelper(new ElementBuilder(elements)
	 * 	.text("Yes")
	 * 	.that(Condition.CONTAIN);}
	 * </pre>
	 * @throws TestException
	 */
	public ElementHelper(ElementBuilder builder) throws TestException {
		if (builder.elementList != null
				&& !builder.elementList.isEmpty()) {
			if (builder.condition == Condition.CONTAIN) {
				webElement = builder.elementList.stream().filter(d -> d.getText().toLowerCase().contains(builder.elementText.toLowerCase())).findAny()
						.orElse(null);
			} else if (builder.condition == Condition.EQUAL) {
				webElement = builder.elementList.stream().filter(d -> d.getText().equalsIgnoreCase(builder.elementText)).findAny()
						.orElse(null);
			}
			
		} else if (builder.element != null) {
			webElement = SHelper.get().element().get(builder.element);
		} else { 
			throw LocalValidation.getValidations().assertionFailed("Please provide either an element or a list of elements.");
		}
	}

	/**
	 * <p> This method is required in order to return the element
	 * that was found when the ElementHelper was executed.
	 * </p>
	 * @return the WebElement
	 */
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
		
		/**
		 * <p> This creates an instance of ElementBuilder which utilizes
		 * a builder pattern to assign values to important variables used in
		 * the ElementHelper class.
		 * </p>
		 * <pre>
		 * Ex:
		 * List<WebElement> elements = SHelper.get().element().getListOf(Generic.ELEMENT.element()); 
		 * {@code new ElementHelper(new ElementBuilder(elements)
		 * 	.text("Yes")
		 * 	.that(Condition.CONTAIN);}
		 * </pre>
		 * @param elementList - the list of elements that will be filtered through
		 */
		public ElementBuilder(List<WebElement> elementList) {
			this.elementList = elementList;
			if (condition == null) {
				condition = Condition.EQUAL;
			}
		}
		
		/**
		 * <p> This creates an instance of ElementBuilder which utilizes
		 * a builder pattern to assign values to important variables used in
		 * the ElementHelper class.
		 * </p>
		 * <pre>
		 * Ex:
		 * List<WebElement> elements = SHelper.get().element().getListOf(Generic.ELEMENT.element()); 
		 * {@code new ElementHelper(new ElementBuilder(elements)
		 * 	.text("Yes")
		 * 	.that(Condition.CONTAIN);}
		 * </pre>
		 * @param elementList - the list of elements that will be filtered through
		 */
		public ElementBuilder(TestElement element) {
			this.element = element;
		}
		
		/**
		 * <p> Tells the ElementHelper which text value needs to be
		 * used to find a particular element in a list of elements.
		 * </p>
		 * <p> This is not a required method. If a value is not 
		 * given, then ElementHelper will use the standard SHelper get().element() method.
		 * </p>
		 * <pre>
		 * Ex:
		 * List<WebElement> elements = SHelper.get().element().getListOf(Generic.ELEMENT.element()); 
		 * {@code new ElementHelper(new ElementBuilder(elements)
		 * 	.text("Yes"));}
		 * </pre>
		 * @param text - the text value to be found in the list of elements
		 */
		public ElementBuilder text(String text) {
			this.elementText = text;
			return this;
		}
		
		/**
		 * <p> Tells the ElementHelper which condition to use
		 * in order to find a particular text in a list of elements.
		 * </p>
		 * <p> This is not a required method. The default value is set to 
		 * Condition.EQUAL if a condition is not provided. Though it
		 * is nice to specify which condition is to be used for readability purposes.
		 * </p>
		 * <pre>
		 * Ex:
		 * List<WebElement> elements = SHelper.get().element().getListOf(Generic.ELEMENT.element()); 
		 * {@code new ElementHelper(new ElementBuilder(elements)
		 * 	.text("Yes")
		 * 	.that(Condition.CONTAIN);}
		 * </pre>
		 * @param text - the text value to be found in the list of elements
		 */
		public ElementBuilder that(Condition condition) {
			this.condition = condition;
			return this;
		}
	}
}
