package commonClasses.sharedUtils;


import reporting.framework.utilities.*;
import junit.framework.AssertionFailedError;

public class MsTESTReport {
	
	public MsTESTReport()
    {

    }

    public FrameworkException reportAssertionFailed(AssertionFailedError assertionFailedError) throws FrameworkException
    {
    	FrameworkException exception = new FrameworkException(assertionFailedError.toString());
    	throw(exception);
    }

}
