package com.page.utils;

import java.util.List;
import java.util.Objects;

import com.config.SHelper;
import com.config.TestException;
import com.config.setup.report.LocalReport;
import com.config.setup.report.LocalValidation;
import com.selenium.TestElement;
import com.utils.Validator;
import com.page.core.PageUtils;
import com.page.core.web.Type;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import com.selenium.wait.Condition;

@Slf4j
public class OptionSelector extends PageUtils {

	private Type type;
	private TestElement telement;
	private List<WebElement> element;
	private String option;
	private Condition condition;

	/**
	 * <p> A helper to find some element in a list of
	 * elements and select it based on the visible
	 * text that displays within the element. It utilizes 
	 * the ElementHelper.
	 * </p>
	 * <p> The primary difference between this helper and
	 * ElementHelper is that this actually selects an option
	 * in addition to locating an element in a list of elements.
	 * It's a layer on top of ElementHelper to add 
	 * needed functionality in addition.
	 * </p>
	 * <p> If the text is not found in any element in the
	 * list of elements, the test fails.
	 * </p>
     * <pre>
     * Ex: 
     * {@code new OptionSelector(new OptionSelector()
	 *	.findOption("Test 2")
	 *	.thatIs(Condition.EQUAL)
	 *	.For(Generic.ELEMENT.element()));}
     * </pre>
	 * @throws TestException
	 */
	public OptionSelector() throws TestException {
		if (condition == null) {
			this.condition = Condition.INVALID_CONDITION;
		}
	}

	private void findOptionThatIsEqualToOptionInAList() throws TestException {
		try {
			//SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(type.element());
			List<WebElement> webElements = SHelper.get().element().getListOf(type.element());
			WebElement element = null;
			try {
				element = find(webElements).that(Condition.EQUAL).text(option).get();
				click().on(type).using(element).start();
				LocalReport.getReport().reportDoneEvent(log, option + " has been selected successfully.");
			} catch (Exception e2) {
				throw LocalValidation.getValidations().assertionFailed(log, option + " is not found in the "
						+ "list of available options. Unable to select the expected option.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	private void findOptionThatIsEqualToOptionInAWebElementList() throws TestException {
		try {
			WebElement el = null;
			try {
				el = find(element).that(Condition.EQUAL).text(option).get();
				click().on(type).using(el).start();
				LocalReport.getReport().reportDoneEvent(log, option + " has been selected successfully.");
			} catch (Exception e2) {
				throw LocalValidation.getValidations().assertionFailed(log, option + " is not found in the "
						+ "list of available options. Unable to select the expected option.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	private void findTheOptionContainedInAList() throws TestException {
		try {
			//SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(type.element());
			List<WebElement> webElements = SHelper.get().element().getListOf(type.element());
			WebElement element = null;
			try {
				element = find(webElements).that(Condition.CONTAIN).text(option).get();
				click().on(type).using(element).start();
				LocalReport.getReport().reportDoneEvent(log, option + " has been selected successfully.");
			} catch (Exception e2) {
				throw LocalValidation.getValidations().assertionFailed(log, option + " is not found in the "
						+ "list of available options. Unable to select the expected option.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	private void findTheOptionContainedInAWebElementList() throws TestException {
		try {
			WebElement el = null;
			try {
				el = find(element).that(Condition.CONTAIN).text(option).get();
				click().on(type).using(el).start();
				LocalReport.getReport().reportDoneEvent(log, option + " has been selected successfully.");
			} catch (Exception e2) {
				throw LocalValidation.getValidations().assertionFailed(log, option + " is not found in the "
						+ "list of available options. Unable to select the expected option.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	/**
	 * <p> Tells the OptionSelector which text value needs to be
	 * used to find a particular element in a list of elements.
	 * </p>
	 * <p> This is a required method. If a value is not 
	 * given, then OptionSelector will throw an error and request for the 
	 * findOption() method to be added.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code new OptionSelector(new OptionSelector()
	 *	.findOption("Test 2")
	 *	.thatIs(Condition.EQUAL)
	 *	.For(Generic.ELEMENT.element()));}
	 * </pre>
	 */
	public OptionSelector findOption(String option) {
		this.option = option;
		return this;
	}

	/**
	 * <p> Tells the OptionSelector which condition to use
	 * in order to find a particular text in a list of elements.
	 * </p>
	 * <p> This is a required method. If the condition is not 
	 * given, then OptionSelector will throw an error and request for the 
	 * thatIs() method to be added.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code new OptionSelector(new OptionSelector()
	 *	.findOption("Test 2")
	 *	.thatIs(Condition.EQUAL)
	 *	.For(Generic.ELEMENT.element()));}
	 * </pre>
	 */
	public OptionSelector thatIs(Condition value) {
		this.condition = value;
		return this;
	}

	/**
	 * <p> Tells the OptionSelector the TestElement in order to gather the total list
	 * of option elements so that a single option can be selected 
	 * from the list.
	 * </p>
	 * <p> This is a required method. If the options TestElement is not 
	 * given, then OptionSelector will throw an error and request for the 
	 * in() method to be added.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code new OptionSelector(new OptionSelector()
	 *	.findOption("Test 2")
	 *	.thatIs(Condition.EQUAL)
	 *	.For(Generic.ELEMENT.element()));}
	 * </pre>
	 */
	public OptionSelector in(Type element) {
		this.type = element;
		return this;
	}

	/**
	 * <p> Tells the OptionSelector the TestElement in order to gather the total list
	 * of option elements so that a single option can be selected
	 * from the list.
	 * </p>
	 * <p> This is a required method. If the options TestElement is not
	 * given, then OptionSelector will throw an error and request for the
	 * in() method to be added.
	 * </p>
	 * <pre>
	 * Ex:
	 * {@code new OptionSelector(new OptionSelector()
	 *	.findOption("Test 2")
	 *	.thatIs(Condition.EQUAL)
	 *	.For(Generic.ELEMENT.element()));}
	 * </pre>
	 */
	public OptionSelector using(List<WebElement> element) {
		this.element = element;
		return this;
	}

	public OptionSelector start() throws TestException {
		try {
			Validator.of(option).validate(Objects::nonNull, result -> option != null, "Option is null. Add the findOption() method.")
					.get();
			Validator.of(type)
					.validate(Objects::nonNull, result -> type != null, "Test element is null. Add the in() method.").get();
			if (element != null) {
				switch (condition) {
					case EQUAL:
						findOptionThatIsEqualToOptionInAWebElementList();
						break;
					case CONTAIN:
						findTheOptionContainedInAWebElementList();
						break;
					default:
						throw new TestException(
								"Please select a valid condition. Add the thatIs() method.");
				}
			} else {
				switch (condition) {
					case EQUAL:
						findOptionThatIsEqualToOptionInAList();
						break;
					case CONTAIN:
						findTheOptionContainedInAList();
						break;
					default:
						throw new TestException(
								"Please select a valid condition. Add the thatIs() method.");
				}
			}
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
		return this;
	}

}
