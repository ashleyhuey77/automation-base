package unitTests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Test;
import common.basePage.interfaces.DatePicker;
import common.basePage.methods.FutureDate;
import common.basePage.methods.PastDate;
import common.basePage.methods.PresentDate;

public class DatePickerTests {
	
	@Test
	public void verifyGetFutureDate() throws Exception {
		DatePicker picker = new FutureDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-YYYY");
        Date now = new Date();
        String date = formatDate.format(now).toString();
		
		String timeStamp = picker.getDate("dd-MM-YYYY", 1);
		
		Assert.assertNotEquals(timeStamp, date);;
	}
	
	@Test
	public void verifyGetPastDate() throws Exception {
		DatePicker picker = new PastDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-YYYY");
        Date now = new Date();
        String date = formatDate.format(now).toString();
		
		String timeStamp = picker.getDate("dd-MM-YYYY", 1);
		
		Assert.assertNotEquals(timeStamp, date);;
	}
	
	@Test
	public void verifyGetPresentDate() throws Exception {
		DatePicker picker = new PresentDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-YYYY");
        Date now = new Date();
        String date = formatDate.format(now).toString();
		
		String timeStamp = picker.getDate("dd-MM-YYYY");
		
		Assert.assertEquals(timeStamp, date);;
	}

}
