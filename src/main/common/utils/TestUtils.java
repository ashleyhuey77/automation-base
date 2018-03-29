package common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	/**
	 * <p>This method is used to get the relative path of the test framework.</p>
	 * @return String
	 */
    public static String getRelativePath() {
        String getFrameWorkRelativePath = System.getProperty("user.dir");
        return getFrameWorkRelativePath;
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
    
    /**
     * <p>This method takes an array of strings and selects a random
     * string from the array.</p>
     * @param strings - the array of strings that a string value should be randomly 
     * selected from
     * @return String
     * @throws Exception
     */
    public static String getRandomString(String[] strings) throws Exception {
    	String result = null;
    	try {
            List < String > stringArray = new ArrayList < String > ();
            for (String i: strings) {
                stringArray.add(i);
            }
            Collections.shuffle(stringArray);
            result = stringArray.get(0);
		} catch (Exception e) {
			throw e;
		}
    	return result;
    }

}