package reporting.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static String GetCurrentFormattedTime(String dateFormatString)
    { 
        DateFormat formatDate = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		Date now = new Date();
        return formatDate.format(now).toString();
    }

    public static String GetFileSeparator()
    {
        return File.separator;
    }

}
