package com.page.utils.date;

import com.config.TestException;

/**
 * <p>
 * Gets a date based on the requested format and
 * how many days into the future. The date can be
 * retrieved in the following ways:
 * </p>
 * 
 * <pre>
 * 	- Date.FUTURE_DATE: This requires the numOfDays param to
	 * 	select a date that is not a present or past date.
	 * 	- Date.PAST_DATE: This requires the numOfDays param.
	 * 	It will select a date that is not in the future or in the present.
	 * 	- Date.PRESENT_DATE: This does not require the numOfDays param.
	 * 	It will select a date that is not in the past or in the future
	 * 	using LocalDateTime.now().
 * </pre>
 * <p>
 * It should be noted that each of these methods
 * of retrieving dates requires specific code that
 * varies based on the Date condition (FUTURE vs.
 * PAST vs. PRESENT)
 * </p>
 * <p>
 * The date will be returned in whatever date
 * format that is specified in the format param
 * (MM/dd/YYYY, dd/MM/yy, etc.)
 * </p>
 * 
 * @author ashleyhuey
 */
public interface DatePicker {

	/**
	 * <p>
	 * Gets a date based on the requested format and
	 * how many days into the future. The date can be
	 * retrieved in the following ways:
	 * </p>
	 * 
	 * <pre>
	 * 	- Date.FUTURE_DATE: This requires the numOfDays param to
	 * 	select a date that is not a present or past date.
	 * 	- Date.PAST_DATE: This does not require the numOfDays param.
	 * 	It will select a date that is not in the future or in the present.
	 * 	- Date.PRESENT_DATE: This does not require the numOfDays param.
	 * 	It will select a date that is not in the past or in the future
	 * 	using LocalDateTime.now().
	 * </pre>
	 * <p>
	 * It should be noted that each of these methods
	 * of retrieving dates requires specific code that
	 * varies based on the Date condition (FUTURE vs.
	 * PAST vs. PRESENT)
	 * </p>
	 * <p>
	 * The date will be returned in whatever date
	 * format that is specified in the format param
	 * (MM/dd/YYYY, dd/MM/yy, etc.)
	 * </p>
	 * 
	 * @param format
	 *            - the way the date should be
	 *            formatted
	 * @param numOfDays
	 *            - how many days out the returned
	 *            date should be
	 * @return a date in a string
	 * @throws TestException
	 */
	public String getDate(String format, int... numOfDays) throws TestException;
}
