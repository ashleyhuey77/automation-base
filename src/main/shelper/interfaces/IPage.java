package shelper.interfaces;

import log.TestException;
import shelper.enums.Location;

public interface IPage {
	
	/**
	 * <p>This method is meant to simulate scrolling to a particular location on the 
	 * page.</p>
	 * <p>Note that this method will not scroll to a webelement. There are methods
	 * for that, however they are located in actions not in page.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().page().scrollTo(Location.TOP_OF_PAGE);}
	 * </pre>
	 * @throws TestException
	 */
	public void scrollTo(Location location) throws TestException;
	
	/**
	 * <p>This method is meant to refresh the current page. It will not wait for
	 * any elements to be present however. So be sure to call that functionality after
	 * refreshing the page to reduce timing issues.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().page().refresh();}
	 * </pre>
	 * @throws TestException
	 */
	public void refresh() throws TestException;

}
