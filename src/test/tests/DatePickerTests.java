package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.config.TestException;
import com.page.utils.date.DatePicker;
import com.page.utils.date.FutureDate;
import com.page.utils.date.PastDate;
import com.page.utils.date.PresentDate;

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
