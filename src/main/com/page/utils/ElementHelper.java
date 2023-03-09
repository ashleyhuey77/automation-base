package com.page.utils;

import java.util.List;
import java.util.Objects;

import com.config.SHelper;
import com.config.TestException;
import com.config.setup.report.LocalValidation;
import com.selenium.TestElement;
import com.utils.Validator;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import com.selenium.wait.Condition;

@Slf4j
public class ElementHelper {

	//required
	private List<WebElement> elementList;
	private TestElement element;
	WebElement webElement;

	//optional
	private String elementText;
	private Condition condition;
	
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
	 * {@code new ElementHelper(new ElementHelper(elements)
	 * 	.text("Yes")
	 * 	.that(Condition.CONTAIN);}
	 * </pre>
	 * @throws TestException
	 */
	public ElementHelper(List<WebElement> elementList) throws TestException {
		this.elementList = elementList;
		if (condition == null) {
			condition = Condition.EQUAL;
		}
	}

	/**
	 * <p> This creates an instance of ElementHelper which utilizes
	 * a builder pattern to assign values to important variables used in
	 * the ElementHelper class.
	 * </p>
	 * <pre>
	 * Ex:
	 * List<WebElement> elements = SHelper.get().element().getListOf(Generic.ELEMENT.element()); 
	 * {@code new ElementHelper(new ElementHelper(elements)
	 * 	.text("Yes")
	 * 	.that(Condition.CONTAIN);}
	 * </pre>
	 */
	public ElementHelper(TestElement element) {
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
	 * {@code new ElementHelper(new ElementHelper(elements)
	 * 	.text("Yes"));}
	 * </pre>
	 * @param text - the text value to be found in the list of elements
	 */
	public ElementHelper text(String text) {
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
	 * {@code new ElementHelper(new ElementHelper(elements)
	 * 	.text("Yes")
	 * 	.that(Condition.CONTAIN);}
	 * </pre>
	 */
	public ElementHelper that(Condition condition) {
		this.condition = condition;
		return this;
	}

	public WebElement get() throws TestException {
		if (elementList != null
				&& !elementList.isEmpty()) {
			if (condition == Condition.CONTAIN) {
				webElement = elementList.stream().filter(d -> d.getText().toLowerCase().contains(elementText.toLowerCase())).findFirst()
						.orElse(null);
				Validator.of(webElement).validate(Objects::nonNull, result -> webElement != null, "The element returned null. Unable to find the element with text " + elementText + " on this page.").get();

			} else if (condition == Condition.EQUAL) {
				webElement = elementList.stream().filter(d -> d.getText().equalsIgnoreCase(elementText)).findFirst()
						.orElse(null);
				Validator.of(webElement).validate(Objects::nonNull, result -> webElement != null, "The element returned null. Unable to find the element with text " + elementText + " on this page.").get();
			}

		} else if (element != null) {
			webElement = SHelper.get().element().get(element);
		} else {
			throw LocalValidation.getValidations().assertionFailed(log, "Please provide either an element or a list of elements.");
		}
		return webElement;
	}
}
