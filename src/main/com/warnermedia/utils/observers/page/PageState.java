package com.warnermedia.utils.observers.page;

import java.time.Duration;
import java.util.logging.Level;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.page.core.web.BaseGeneric;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.text.Variable;
import com.warnermedia.utils.Log;
import com.warnermedia.utils.observers.ErrorType;
import com.warnermedia.utils.observers.Observable;

public class PageState extends Observable<PageState, Page, ErrorType> {
	
	private ErrorType currentState;
	
	public PageState() {
		currentState = ErrorType.NONE;
	}
	
	/**
	 * @throws TestException 
	 * 
	 */
	public void checkState() throws TestException {
		if (SHelper.get().element().isDisplayed(BaseGeneric.DISMISS_BUTTON.element(), Duration.ofSeconds(1))) {
			String text = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(BaseGeneric.MODAL_MESSAGE.element());
			if (text.toLowerCase().contains(ErrorType.AFTER_SCHEDULED_EXPORT_EVENT.getDescription())
					|| text.toLowerCase().contains(ErrorType.UNEXPECTED_STOP_EVENT.getDescription())) {
				currentState = ErrorType.AFTER_SCHEDULED_EXPORT_EVENT;
			} else if (text.toLowerCase().contains(ErrorType.MISSING_ASSET.getDescription())
				|| text.toLowerCase().contains(ErrorType.UNABLE_TO_ACCESS_MEDIASOURCE.getDescription())) {
				currentState = ErrorType.MISSING_ASSET;
			}
		}
		Log.get().log(Level.INFO, currentState.name(), "Page state is {0}");
		notifyObservers(currentState);
		currentState = ErrorType.NONE;
	}

}
