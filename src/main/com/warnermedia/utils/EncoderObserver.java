package com.warnermedia.utils;

import java.util.logging.Level;


import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.page.core.web.BaseGeneric;

public class EncoderObserver implements Page {

	@Override
	public void update(State state, StateType stateType) throws TestException {
		try {
			switch (stateType) {
				case FS100:
					LocalValidation.getValidations().assertionPass("Screencap of the error that displays as taken by the observer.");
					SHelper.get().element().get(BaseGeneric.DISMISS_BUTTON.element()).click(); 
					break;
				case MISSING_ASSET:
					LocalValidation.getValidations().assertionPass("Screencap of the error that displays as taken by the observer.");
					SHelper.get().element().get(BaseGeneric.DISMISS_BUTTON.element()).click();
					break;
				default:
					break;
					
			}
		} catch (Exception e) {
			Log.get().log(Level.WARNING, "An attempt to clear the error was made but the click was not successful. The error may no longer be displayed.");
		}
	}

}
