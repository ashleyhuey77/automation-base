package com.report;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	private Util() {
		
	}
	
	public static String getCurrentFormattedTime()
    { 
        DateFormat formatDate = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		Date now = new Date();
        return formatDate.format(now);
    }

    public static String getFileSeparator()
    {
        return File.separator;
    }

}
