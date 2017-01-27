package reporting.framework.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static String GetCurrentFormattedTime(String dateFormatString)
    {
        /*DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar now = Calendar.getInstance();
        String str = String.format("{0:"+ dateFormatString + "}", date.format(now.getTime().toString()));*/
        
        DateFormat formatDate = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		Date now = new Date();
        return formatDate.format(now).toString();
    }

    public static String GetFileSeparator()
    {
        return File.separator;
    }

   /*
    public static String GetFormattedTime(DateTime time, String dateFormatString)
    {
        return String.format(dateFormatString, time);
    }*/

   /* public static String GetTimeDifference(DateTime startTime, DateTime endTime)
    {
        TimeSpan timeSpan = endTime.Subtract(startTime);
        double totalMinutes = timeSpan.TotalMinutes;
        string str = totalMinutes.ToString();
        totalMinutes = timeSpan.TotalSeconds;
        string str1 = string.Concat(str, " minute(s), ", totalMinutes.ToString(), " seconds");
        return str1;
    }*/
}
