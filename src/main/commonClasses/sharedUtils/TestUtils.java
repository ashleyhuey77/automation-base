package commonClasses.sharedUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
	 * <p>This method gets the current DateTime and returns it in
	 * the format specified by the params. Typical date formats include:</p>
	 * <p>1. dd/MM/yyyy </br>
	 * 	  2. MM/dd/yyyy </br>
	 *    3. MM/dd/yy </br>
	 *    4. MMM dd yyyy </br>
	 * </p>
	 * @param format - the date format that the returned date string
	 * should be in.
	 * @return String
	 */
    public static String GetCurrentDateTime(String format) {
        DateFormat formatDate = new SimpleDateFormat(format);
        Date now = new Date();
        return formatDate.format(now).toString();
    }

    /**
     * <p>Gets the current timestamp and returns it in the MM_dd_yyyy_HHmmss format</p>
     * <p>Specifically used for reporting purposes as to assign a unique name for each report
     * folder to prevent overwriting and better organize the reports. However, it can be used for 
     * other purposes as well.</p>
     * <p>The date format should not be changed in this method as it is crucial to reporting.
     * The GetCurrentDateTime method Should be used for any date stamps that are not the one
     * specified in this method.</p>
     * @return String
     */
    public static String getTimeStamp() {
        String getCurrentTimeStamp = GetCurrentDateTime("MM_dd_yyyy_HHmmss");
        return getCurrentTimeStamp;
    }

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