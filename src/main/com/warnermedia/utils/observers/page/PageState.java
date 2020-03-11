package com.warnermedia.utils.observers.page;

import java.util.logging.Level;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.page.core.web.BaseGeneric;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.text.Variable;
import com.warnermedia.utils.Log;
import com.warnermedia.utils.observers.Observable;

public class PageState extends Observable<PageState, Page, StateType> {
	
	private StateType currentState;
	
	public PageState() {
		currentState = StateType.GOOD;
	}
	
	/**
	 * @throws TestException 
	 * 
	 */
	public void checkState() throws TestException {
		if (SHelper.get().element().isDisplayed(BaseGeneric.DISMISS_BUTTON.element(), 1)) {
			String text = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(BaseGeneric.MODAL_MESSAGE.element());
			if (text.toLowerCase().contains("after scheduled export event started")
					|| text.toLowerCase().contains("unexpected stop event detected and ignored in record state")) {
				currentState = StateType.FS100;
			} else if (text.toLowerCase().contains("mediasource returned an error indicating that this asset could not be retreived")) {
				currentState = StateType.MISSING_ASSET;
			}
		}
		Log.get().log(Level.INFO, currentState.name(), "Page state is {0}");
		notifyObservers(currentState);
		currentState = StateType.GOOD;
	}

}
