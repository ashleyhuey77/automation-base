package com.warnermedia.config.os;

import com.warnermedia.config.State;
import com.warnermedia.config.TestException;

public class OSFacade {
	
	public static void setDriverLocalPathBasedOnOS(OS os) throws TestException {
		State state = null;
		OSContext context = new OSContext();
		switch (os) {
			case MAC:
				state = new Mac();
				break;
			case WINDOWS:
				state = new Windows();
				break;
			case LINUX:
				state = new Linux();
				break;
			default:
				throw new TestException(
						"User did not provide a valid operating system. Unable to open browser because the operating system is unknown.");
		}
		context.setState(state);
		context.doAction();
	}

}
