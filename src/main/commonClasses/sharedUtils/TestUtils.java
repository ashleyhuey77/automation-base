package commonClasses.sharedUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {
	
	public static String GetCurrentDateTime(String format)
    {
		DateFormat formatDate = new SimpleDateFormat(format);
		Date now = new Date();
        return formatDate.format(now).toString();
    }
	
	/// <summary>
    /// Get Current time stamp
    /// @return
    /// </summary>
    public static String getTimeStamp()
    {
        String getCurrentTimeStamp = GetCurrentDateTime("dd-MM-yy HH:mm:ss");
        return String.format(getCurrentTimeStamp, "yyyy_MMM_dd_HH_mm_ss");
    }

    /// <summary>
    /// Get relative path of the framework
    /// @return
    /// </summary>
    public static String getRelativePath()
    {
            //String frameWorkRelativePath = "";
            String getFrameWorkRelativePath = System.getProperty("user.dir");

            /*if (getFrameWorkRelativePath.contains("bin"))
                frameWorkRelativePath = getFrameWorkRelativePath.substring(0, getFrameWorkRelativePath.indexOf("\\bin\\") + 1);
            else
                frameWorkRelativePath = getFrameWorkRelativePath;*/

            //Trace.WriteLine("Trace: " + frameWorkRelativePath);
            return getFrameWorkRelativePath;
    }

}
