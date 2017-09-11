package commonClasses.sharedUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TestUtils {

    public static String GetCurrentDateTime(String format) {
        DateFormat formatDate = new SimpleDateFormat(format);
        Date now = new Date();
        return formatDate.format(now).toString();
    }

    /// <summary>
    /// Get Current time stamp
    /// @return
    /// </summary>
    public static String getTimeStamp() {
        String getCurrentTimeStamp = GetCurrentDateTime("dd_MM_yy_HHmmss");
        return getCurrentTimeStamp;
    }

    /// <summary>
    /// Get relative path of the framework
    /// @return
    /// </summary>
    public static String getRelativePath() {
        String getFrameWorkRelativePath = System.getProperty("user.dir");
        return getFrameWorkRelativePath;
    }

    public static boolean isNullOrBlank(String param) {
        return param == null || param.trim().length() == 0;
    }
    
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