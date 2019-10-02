package tests.utils;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.warnermedia.config.TestException;
import com.warnermedia.utils.RandomStringHelper;
import com.warnermedia.utils.RandomStringHelper.RandomStringBuilder;

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
			Assert.assertTrue(!test.equals(""), "Value returned an empty string value.");
			Assert.assertTrue(doesResultContainArrayValue(test, stringArray), "The string array does not contain the resulting value.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	public void getRandomStringFromList() throws TestException {
		try {
			//arrange
			List<String> stringArray = new ArrayList<>();
					stringArray.add("Test1");
					stringArray.add("Test2"); 
					stringArray.add("Test3");
			//act
			String test = new RandomStringHelper(new RandomStringBuilder()
										.buildAString(stringArray))
										.get();
			//assert
			Assert.assertNotNull(test);
			Assert.assertTrue(!test.equals(""), "Value returned an empty string value.");
			Assert.assertTrue(doesResultContainArrayValue(test, stringArray), "The string array does not contain the resulting value.");
		} catch (Exception e) {
			throw e;
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
			Assert.assertTrue(!test.equals(""), "Value returned an empty string value.");
			Assert.assertTrue(test.length() <= 5, "The length of the string is greater than it should be.");
		} catch (Exception e) {
			throw e;
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
			Assert.assertTrue(!test.equals(""), "Value returned an empty string value.");
			Assert.assertTrue(test.length() == 5, "The length of the string is not exactly 5 as it should be.");
		} catch (Exception e) {
			throw e;
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
			Assert.assertTrue(!test.equals(""), "Value returned an empty string value.");
			Assert.assertTrue(test.length() == 5, "The length of the string is not exactly 5 as it should be.");
		} catch (Exception e) {
			throw e;
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
	
	@Test
	public void getRandomStringFromArray_IsPartialTrue() throws TestException {
		try {
			//arrange
			String[] stringArray = new String[] { "Test1", "Test2", "Test3" };
			//act
			String test = new RandomStringHelper(new RandomStringBuilder()
										.buildAString(stringArray)
										.returnAPartialString(true))
										.get();
			//assert
			Assert.assertNotNull(test);
			Assert.assertTrue(!test.equals(""), "Value returned an empty string value.");
			Assert.assertTrue(doesResultContainArrayValue(test, stringArray), "The string array does not contain the resulting value.");
			System.out.println(test);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	public void getRandomStringFromList_IsPartialTrue() throws TestException {
		try {
			//arrange
			List<String> stringArray = new ArrayList<>();
					stringArray.add("TestingHi1");
					stringArray.add("TesingYou2"); 
					stringArray.add("TetingFive3");
			//act
			String test = new RandomStringHelper(new RandomStringBuilder()
										.buildAString(stringArray)
										.returnAPartialString(true))
										.get();
			//assert
			Assert.assertNotNull(test);
			Assert.assertTrue(!test.equals(""), "Value returned an empty string value.");
			Assert.assertTrue(doesResultContainArrayValue(test, stringArray), "The string array does not contain the resulting value.");
			System.out.println(test);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	public void getRandomStringAtARandomLength_IsPartialTrue() throws TestException {
		try {
			//arrange
			String characters = "TestingTime";
			//act
			String test = new RandomStringHelper(new RandomStringBuilder()
										.buildAString(characters)
										.returnAPartialString(true))
										.get();
			//assert
			Assert.assertNotNull(test);
			Assert.assertTrue(!test.equals(""), "Value returned an empty string value.");
			System.out.println(test);
		} catch (Exception e) {
			throw e;
		}
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
	
	private Boolean doesResultContainArrayValue(String value, List<String> array) {
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
