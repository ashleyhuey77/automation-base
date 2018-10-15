package common.base.methods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import common.base.interfaces.DatePicker;
import log.TestException;

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
