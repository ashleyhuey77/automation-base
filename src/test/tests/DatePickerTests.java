package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.warnermedia.config.SHelper;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.text.Variable;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.warnermedia.config.TestException;
import com.warnermedia.page.utils.date.DatePicker;
import com.warnermedia.page.utils.date.FutureDate;
import com.warnermedia.page.utils.date.PastDate;
import com.warnermedia.page.utils.date.PresentDate;

public class DatePickerTests {
	
	@Test
	public void verifyGetFutureDate() throws TestException {
		DatePicker picker = new FutureDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String date = formatDate.format(now);
		
		String timeStamp = picker.getDate("dd-MM-yyyy", 1);
		new PastDate().getDate("MMddyyyy", 1);
		
		Assert.assertNotEquals(timeStamp, date);
	}
	
	@SuppressWarnings("unused")
	@Test(expectedExceptions=Exception.class)
	public void verifyGetFutureDate_ThrowsException() throws TestException {
		DatePicker picker = new FutureDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String date = formatDate.format(now).toString();
		
		String timeStamp = picker.getDate(null, 1);
	}
	
	@SuppressWarnings("unused")
	@Test(expectedExceptions=Exception.class)
	public void verifyGetFutureDate_NoNumOfDaysProvided() throws TestException {
		DatePicker picker = new FutureDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String date = formatDate.format(now).toString();
		
		String timeStamp = picker.getDate(date);
	}
	
	@Test
	public void verifyGetPastDate() throws TestException {
		DatePicker picker = new PastDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String date = formatDate.format(now).toString();
		
		String timeStamp = picker.getDate("dd-MM-yyyy", 1);
		
		Assert.assertNotEquals(timeStamp, date);;
	}
	
	@SuppressWarnings("unused")
	@Test(expectedExceptions=Exception.class)
	public void verifyGetPastDate_ThrowsException() throws TestException {
		DatePicker picker = new PastDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String date = formatDate.format(now).toString();
		
		String timeStamp = picker.getDate(null, 1);
	}
	
	@SuppressWarnings("unused")
	@Test(expectedExceptions=Exception.class)
	public void verifyGetPastDate_NumOfDaysNotProvided() throws TestException {
		DatePicker picker = new PastDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String date = formatDate.format(now).toString();
		
		String timeStamp = picker.getDate(date);
	}
	
	@Test
	public void verifyGetPresentDate() throws TestException {
		DatePicker picker = new PresentDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String date = formatDate.format(now).toString();
		
		String timeStamp = picker.getDate("dd-MM-yyyy");
		
		Assert.assertEquals(timeStamp, date);;
	}
	
	@SuppressWarnings("unused")
	@Test(expectedExceptions=Exception.class)
	public void verifyGetPresentDate_ThrowsException() throws TestException {
		DatePicker picker = new PresentDate();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String date = formatDate.format(now).toString();
		
		String timeStamp = picker.getDate(null);
	}

}
