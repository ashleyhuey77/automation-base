package common.base.methods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import common.base.interfaces.DatePicker;
import log.TestException;

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
