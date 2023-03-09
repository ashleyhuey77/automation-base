package com.utils;

/**
 * <h2>TestUtils</h2>
 * <p>The TestUtils class is meant to hold methods that are used accross
 * all the different class types (i.e application, page, modal, api, etc.).</p>
 * <p>It is home to all the static void methods that are utility methods for 
 * the test framework.</p>
 * @author ashleyhuey
 *
 */
public class TestUtils {
	
	private TestUtils() {
		
	}

	/**
	 * <p>This method is used to get the relative path of the test framework.</p>
	 * @return String
	 */
    public static String getRelativePath() {
        return System.getProperty("user.dir");
    }

    /**
     * <p>This method evaluates whether a string is null or an empty
     * string and then returns a boolean value based on the result.</p>
     * @param value - the string to be evaluated
     * @return Boolean
     */
    public static boolean isNullOrBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

}