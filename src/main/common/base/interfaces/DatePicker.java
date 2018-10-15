package common.base.interfaces;

import log.TestException;

public interface DatePicker {

	public String getDate(String format, int... numOfDays) throws TestException; 
}
