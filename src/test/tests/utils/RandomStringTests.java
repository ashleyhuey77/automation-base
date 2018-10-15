package tests.utils;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.utils.helpers.RandomStringHelper;
import common.utils.helpers.RandomStringHelper.RandomStringBuilder;
import log.TestException;

public class RandomStringTests {
	
	@Test
	public void getRandomStringFromArray() throws TestException {
		try {
			//arrange
			String[] stringArray = new String[] { "Test1", "Test2", "Test3" };
			//act
			String test = new RandomStringHelper(new RandomStringBuilder()
										.buildAString(stringArray))
										.get();
			//assert
			Assert.assertNotNull(test);
			Assert.assertTrue(test != "", "Value returned an empty string value.");
			Assert.assertTrue(doesResultContainArrayValue(test, stringArray), "The string array does not contain the resulting value.");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void getRandomStringAtARandomLength() throws TestException {
		try {
			//arrange
			String characters = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			//act
			String test = new RandomStringHelper(new RandomStringBuilder()
										.buildAString(characters)
										.withAMaxLengthOf(5)
										.returnsAStringOfRandomLength(true))
										.get();
			//assert
			Assert.assertNotNull(test);
			Assert.assertTrue(test != "", "Value returned an empty string value.");
			Assert.assertTrue(test.length() <= 5, "The length of the string is greater than it should be.");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void getRandomStringAtSetLength() throws TestException {
		try {
			//arrange
			String characters = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			//act
			String test = new RandomStringHelper(new RandomStringBuilder()
										.buildAString(characters)
										.withAMaxLengthOf(5)
										.returnsAStringOfRandomLength(false))
										.get();
			//assert
			Assert.assertNotNull(test);
			Assert.assertTrue(test != "", "Value returned an empty string value.");
			Assert.assertTrue(test.length() == 5, "The length of the string is not exactly 5 as it should be.");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void getRandomStringNotSpecifyingStringLength() throws TestException {
		try {
			//arrange
			String characters = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			//act
			String test = new RandomStringHelper(new RandomStringBuilder()
										.buildAString(characters)
										.withAMaxLengthOf(5))
										.get();
			//assert
			Assert.assertNotNull(test);
			Assert.assertTrue(test != "", "Value returned an empty string value.");
			Assert.assertTrue(test.length() == 5, "The length of the string is not exactly 5 as it should be.");
		} catch (Exception e) {
			
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void getRandomStringNoStringOrStringArraySpecified_ExceptionThrown() throws Exception {
			//act
			String test = new RandomStringHelper(new RandomStringBuilder()
										.withAMaxLengthOf(5))
										.get();
			Assert.fail("Exception was not thrown the following string was created " + test);
	}
	
	private Boolean doesResultContainArrayValue(String value, String[] array) {
		Boolean result = false;
		try {
			String actual = "";
			for (String string : array) {
				actual = actual + " " + string;
			}
			if (actual.trim().contains(value)) {
				result = true;
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

}
