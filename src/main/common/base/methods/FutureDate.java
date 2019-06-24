package common.base.methods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import common.base.interfaces.DatePicker;
import log.TestException;

/**
 * <p>A class that implements the DatePicker interface.
 * It requires a number of days param to specify how many days
 * into the future to return. Uses the DateTimeFormatter
 * to return now() and then uses plusDays() to add the 
 * specified number of days. Throws an error if numOfDays
 * param is not provided. Formats the date based on the needed format.</p>
 * @author ashleyhuey
 */
public class FutureDate implements DatePicker {

	@Override
	public String getDate(String format, int... numOfDays) throws TestException {
		String value = null;
		if (numOfDays.length != 0) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			LocalDateTime dTPlus = LocalDateTime.now().plusDays(numOfDays[0]);
			value = dTPlus.format(formatter);
		} else {
			throw new TestException(
					"The total number of days in order to calculate a future date was not provided. Please specify a number of days.");
		}
		return value;
	}

}
