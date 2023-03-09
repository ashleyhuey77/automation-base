package com.page.utils.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.config.TestException;

/**
 * <p>A class that implements the DatePicker interface.
 * It returns the present date and time. Uses the LocalDateTime
 * to return now(). Formats the date based on the needed format.</p>
 * @author ashleyhuey
 */
public class PresentDate implements DatePicker {

	@Override
	public String getDate(String format, int... numOfDays) throws TestException {
		String value = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime dTPlus = LocalDateTime.now();
		value = dTPlus.format(formatter);
		return value;
	}

}
