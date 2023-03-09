package com.utils.observers.page;


import com.config.SHelper;
import com.config.TestException;
import com.config.setup.report.LocalValidation;
import com.page.core.web.BaseGeneric;
import com.utils.ConsoleDecoration;
import com.utils.observers.ErrorType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncoderObserver implements Page {

	@Override
	public void update(PageState state, ErrorType stateType) throws TestException {
		try {
			switch (stateType) {
				case AFTER_SCHEDULED_EXPORT_EVENT:
					LocalValidation.getValidations().assertionPass(log, "Screencap of the error that displays as taken by the observer.");
					SHelper.get().element().get(BaseGeneric.DISMISS_BUTTON.element()).click();
					break;
				case MISSING_ASSET:
					LocalValidation.getValidations().assertionPass(log, "Screencap of the error that displays as taken by the observer.");
					SHelper.get().element().get(BaseGeneric.DISMISS_BUTTON.element()).click();
					break;
				default:
					break;
					
			}
		} catch (Exception e) {
			log.info("{}{}An attempt to clear the error was made but the click was not successful. The error may no longer be displayed.{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
		}
	}

}
