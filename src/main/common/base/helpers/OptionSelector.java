package common.base.helpers;

import java.util.List;
import java.util.Objects;
import org.openqa.selenium.WebElement;
import common.base.helpers.ClickHelper.ClickBuilder;
import common.base.vobjects.ReportInfo;
import common.utils.Validator;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalValidation;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.builders.WaitBuilder;
import shelper.enums.Condition;
import shelper.enums.Variable;
import shelper.enums.Via;
import shelper.enums.Wait;
import shelper.vobjects.TestElement;

public class OptionSelector {

	public OptionSelector(OptionSelectorBuilder builder) throws TestException {
		try {
			Validator.of(builder.option).validate(Objects::nonNull, result -> builder.option != null, "Option is null.")
					.get();
			Validator.of(builder.element)
					.validate(Objects::nonNull, result -> builder.element != null, "Test element is null.").get();
			switch (builder.condition) {
				case EQUAL:
					findOptionThatIsEqualToOptionInAList(builder);
					break;
				case CONTAIN:
					findTheOptionContainedInAList(builder);
					break;
				default:
					throw new TestException(
							"Please select a valid condition. Unable to execute because condition is not valid.");
			}
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}

	/**
	 * <p>
	 * Method to find some element in a list of
	 * elements and select it based on the visible
	 * text that displays within the element.
	 * </p>
	 * <p>
	 * If the text is not found in any element in the
	 * list of elements, the test fails.
	 * </p>
	 * <p>
	 * The text must exactly equal the expected option
	 * text (letter casing is not a factor), but
	 * spelling and in some cases spacing could cause
	 * the option to not be found in the list.
	 * </p>
	 * 
	 * @param via
	 *            - the method by which to click a web
	 *            element (e.g click via selenium's
	 *            built in click functionality,
	 *            javascript, jquery, etc.)
	 * @param locator
	 *            - the webelement locator string for
	 *            the field that the text should be
	 *            found in. This locator should create
	 *            a list of webelements.
	 * @param by
	 *            - the type of locator being used
	 *            (i.e id, name, csslocator, xpath,
	 *            etc.)
	 * @param expectedOption
	 *            the option (based on text value)
	 *            that is expected to be found in the
	 *            list of options.
	 * @throws TestException
	 */

	protected void findOptionThatIsEqualToOptionInAList(OptionSelectorBuilder builder) throws TestException {
		try {
			List<WebElement> webElements = SHelper.get().element().getListOf(builder.element);
			WebElement element = new ElementHelper(webElements, builder.option).get();
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

	/**
	 * <p>
	 * Method to find some element in a list of
	 * elements and select it based on the visible
	 * text that displays within the element.
	 * </p>
	 * <p>
	 * If the text is not found in any element in the
	 * list of elements, the test fails.
	 * </p>
	 * <p>
	 * The text must only contain the expected option
	 * text (letter casing is not a factor).
	 * </p>
	 * 
	 * @param via
	 *            - the method by which to click a web
	 *            element (e.g click via selenium's
	 *            built in click functionality,
	 *            javascript, jquery, etc.)
	 * @param locator
	 *            - the webelement locator string for
	 *            the field that the text should be
	 *            found in. This locator should create
	 *            a list of webelements.
	 * @param by
	 *            - the type of locator being used
	 *            (i.e id, name, csslocator, xpath,
	 *            etc.)
	 * @param expectedOption
	 *            the option (based on text value)
	 *            that is expected to be found in the
	 *            list of options.
	 * @throws TestException
	 */

	protected void findTheOptionContainedInAList(OptionSelectorBuilder builder) throws TestException {
		try {
			String value = null;
			SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(5)).on(builder.element);
			List<WebElement> webElements = SHelper.get().element().getListOf(builder.element);
			for (int i = 0; i < webElements.size(); i++) {
				WebElement element = webElements.get(i);
				String actualOption = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);
				if (actualOption.toLowerCase().trim().contains(builder.option.toLowerCase().trim())) {
					new ClickHelper(new ClickBuilder(new ReportInfo(builder.option + " option"))
							.clickOn(new TestElement(builder.element.locator(), builder.element.by()))
							.withAnIndexOf(i));
					value = actualOption;
					LocalReport.getReport().reportDoneEvent(builder.option + " has been selected successfully.");
					break;
				}
			}

			if (value == null) {
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

		public OptionSelectorBuilder() {
			if (condition == null) {
				this.condition = Condition.INVALID_CONDITION;
			}
		}

		public OptionSelectorBuilder findOption(String option) {
			this.option = option;
			return this;
		}

		public OptionSelectorBuilder thatIs(Condition value) {
			this.condition = value;
			return this;
		}

		public OptionSelectorBuilder For(TestElement element) {
			this.element = element;
			return this;
		}
	}
}
