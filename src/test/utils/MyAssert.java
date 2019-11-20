package utils;

import com.warnermedia.config.TestException;

public class MyAssert {
	
	public static void assertTrue(Boolean condition) throws Exception {
		try {
			if (condition) {
				System.out.println("Assertion returned true.");
			} else {
				throw new TestException("Assertion returned false.");
			}
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public static void fail(String msg) throws Exception {
		throw new TestException(msg);
	}

}
