package common.basePage.methods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import common.basePage.interfaces.DatePicker;

public class PresentDate implements DatePicker {

	@Override
	public String getDate(String format, int... numOfDays) throws Exception {
		String value = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			LocalDateTime dTPlus = LocalDateTime.now();
			value = dTPlus.format(formatter).toString();
		} catch (Exception e) {
			throw e;
		}
		return value;
	}

}
