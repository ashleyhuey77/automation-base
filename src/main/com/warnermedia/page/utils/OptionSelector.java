package com.warnermedia.page.utils;

import java.util.List;
import java.util.Objects;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.page.utils.ClickHelper.ClickBuilder;
import com.warnermedia.page.utils.ElementHelper.ElementBuilder;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.wait.Condition;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import com.warnermedia.utils.Validator;

public class OptionSelector {

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
     * {@code new OptionSelector(new OptionSelectorBuilder()
	 *	.findOption("Test 2")
	 *	.thatIs(Condition.EQUAL)
	 *	.For(Generic.ELEMENT.element()));}
     * </pre>
	 * @throws TestException
	 */
	public OptionSelector(OptionSelectorBuilder builder) throws TestException {
		try {
			Validator.of(builder.option).validate(Objects::nonNull, result -> builder.option != null, "Option is null. Add the findOption() method.")
					.get();
			Validator.of(builder.element)
					.validate(Objects::nonNull, result -> builder.element != null, "Test element is null. Add the in() method.").get();
			switch (builder.condition) {
				case EQUAL:
					findOptionThatIsEqualToOptionInAList(builder);
					break;
				case CONTAIN:
					findTheOptionContainedInAList(builder);
					break;
				default:
					throw new TestException(
							"Please select a valid condition. Add the thatIs() method.");
			}
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}

	private void findOptionThatIsEqualToOptionInAList(OptionSelectorBuilder builder) throws TestException {
		try {
			List<WebElement> webElements = SHelper.get().element().getListOf(builder.element);
			WebElement element = new ElementHelper(new ElementBuilder(webElements).that(Condition.EQUAL).text(builder.option)).get();
			if (element != null) {
				new ClickHelper(new ClickBuilder(new ReportInfo(builder.option + " option")).clickOn(element));
				LocalReport.getReport().reportDoneEvent(builder.option + " has been selected successfully.");
			} else {
				throw LocalValidation.getValidations().assertionFailed(builder.option + " is not found in the "
						+ "list of available options. Unable to select the expected option.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	private void findTheOptionContainedInAList(OptionSelectorBuilder builder) throws TestException {
		try {
			SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(5)).on(builder.element);
			List<WebElement> webElements = SHelper.get().element().getListOf(builder.element);
			WebElement element = new ElementHelper(new ElementBuilder(webElements).that(Condition.CONTAIN).text(builder.option)).get();
			if (element != null) {
				new ClickHelper(new ClickBuilder(new ReportInfo(builder.option + " option"))
						.clickOn(new TestElement(builder.element.locator(), builder.element.by())));
				LocalReport.getReport().reportDoneEvent(builder.option + " has been selected successfully.");
			} else {
				throw LocalValidation.getValidations().assertionFailed(builder.option + " is not found in the "
						+ "list of available options. Unable to select the expected option.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	public static class OptionSelectorBuilder {

		private TestElement element;
		private String option;
		private Condition condition;

		/**
		 * <p> This creates an instance of OptionSelectorBuilder which utilizes
		 * a builder pattern to assign values to important variables used in
		 * the OptionSelector class.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new OptionSelector(new OptionSelectorBuilder()
		 *	.findOption("Test 2")
		 *	.thatIs(Condition.EQUAL)
		 *	.For(Generic.ELEMENT.element()));}
		 * </pre>
		 * @param info - the text that will be written to the test results report
		 */
		public OptionSelectorBuilder() {
			if (condition == null) {
				this.condition = Condition.INVALID_CONDITION;
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
		 * {@code new OptionSelector(new OptionSelectorBuilder()
		 *	.findOption("Test 2")
		 *	.thatIs(Condition.EQUAL)
		 *	.For(Generic.ELEMENT.element()));}
		 * </pre>
		 * @param element - the element to be clicked to open the menu
		 */
		public OptionSelectorBuilder findOption(String option) {
			this.option = option;
			return this;
		}

		/**
		 * <p> Tells the OptionSelector which condition to use
		 * in order to find a particular text in a list of elements.
		 * </p>
		 * <p> This is a required method. If the condition is not 
		 * given, then OptionSelectorHelper will throw an error and request for the 
		 * thatIs() method to be added.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new OptionSelector(new OptionSelectorBuilder()
		 *	.findOption("Test 2")
		 *	.thatIs(Condition.EQUAL)
		 *	.For(Generic.ELEMENT.element()));}
		 * </pre>
		 * @param element - the element to be clicked to open the menu
		 */
		public OptionSelectorBuilder thatIs(Condition value) {
			this.condition = value;
			return this;
		}

		/**
		 * <p> Tells the OptionSelector the TestElement in order to gather the total list
		 * of option elements so that a single option can be selected 
		 * from the list.
		 * </p>
		 * <p> This is a required method. If the options TestElement is not 
		 * given, then OptionSelectorHelper will throw an error and request for the 
		 * in() method to be added.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new OptionSelector(new OptionSelectorBuilder()
		 *	.findOption("Test 2")
		 *	.thatIs(Condition.EQUAL)
		 *	.For(Generic.ELEMENT.element()));}
		 * </pre>
		 * @param element - the element to be clicked to open the menu
		 */
		public OptionSelectorBuilder in(TestElement element) {
			this.element = element;
			return this;
		}
	}
}
