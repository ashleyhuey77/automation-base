package common.basePage.methods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import common.basePage.interfaces.DatePicker;

public class PastDate implements DatePicker {

	@Override
	public String getDate(String format, int... numOfDays) throws Exception {
		String value = null;
		try {
			if (numOfDays.length != 0) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    				LocalDate dTPlus = LocalDate.now().minusDays(numOfDays[0]);
    				value = dTPlus.format(formatter).toString();
			} else {
				throw new Exception("The total number of days in order to calculate a past date was not provided. Please specify a number of days.");
			}
		} catch (Exception e) {
			throw e;
		}
		return value;
	}

}
