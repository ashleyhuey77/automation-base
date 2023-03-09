package com.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.config.TestException;
import com.config.setup.report.LocalReport;

public class RandomStringHelper {

	private String result;

	/**
	 * <p>This helper does a few different things to randomize strings 
	 * or to randomly select a string from an array/list. The helper 
	 * can do the following:</p>
	 * <p>
	 	* - Take a string array and return a random string.
	 	 * <pre>
		 * Ex: 
		 * {@code String[] stringArray = new String[] { "Test1", "Test2", "Test3" };
		 *  String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(stringArray))
		 *			.get();}
		 * </pre>
	 * </p>
	 * <p>
	 	* - Take a list and return a random value.
	 	 * <pre>
		 * Ex: 
		 * {@code List<String> stringArray = new ArrayList<>();
		 *	stringArray.add("Test1");
		 *	stringArray.add("Test2"); 
		 *	stringArray.add("Test3");
		 * String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(stringArray))
		 *			.get();}
		 * </pre>
	 *</p>
	 *<p>
	 	* - Take a string and return a partial value at a random length
	 	 * <pre>
		 * Ex: 
		 * {@code String characters = "TestingTime";
		 *  String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(characters)
		 *			.returnAPartialString(true))
		 *			.get();}
		 * </pre>
	 *</p>
	 *<p>
	 	* - Take a string of random characters and return a random string
	 	 * <pre>
		 * Ex: 
		 * {@code String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		 *  String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(characters)
		 *			.withAMaxLengthOf(5))
		 *			.get();}
		 * </pre>
	 * </p>
	 * <p>It uses pre-existing shuffle functionality from the Collections library.
	 * As well as SecureRandom and other various java string manipulations
	 * to function.</p>
	 * <p>For the partial String and String to String methods, they generate a 
	 * random string of text by taking a string of text and scrambling it into
	 * a new string at a randomly selected length that
	 * will not surpass the specified length param.</p>
	 * <p>It should be noted that getting a partial string can apply to 
	 * any of the different helper functionalities. You can get a partial 
	 * from a random value from an array, or string, or string array, etc.</p>
	 * @throws TestException
	 */
	public RandomStringHelper(RandomStringBuilder builder) throws TestException {
		if (builder.stringArray != null && builder.stringArray.length > 0) {
			result = getRandomStringFromArray(builder);
		} else if (builder.stringList != null && !builder.stringList.isEmpty()) {
			result = getRandomStringFromList(builder);
		} else if (!TestUtils.isNullOrBlank(builder.stringValue)) {
			if (builder.isPartialString) {
				result = getPartialString(builder.stringValue);
			} else {
				result = createRandomStringFromCharacters(builder);
			}
		} else {
			throw new TestException("Please provide either a string or a string array to be randomized.");
		}
	}

	private static String getRandomStringFromArray(RandomStringBuilder builder) throws TestException {
		String result = null;
		List<String> stringArray = new ArrayList<>();
		for (String i : builder.stringArray) {
			stringArray.add(i);
		}
		Collections.shuffle(stringArray);
		if (builder.isPartialString) {
			result = getPartialString(stringArray.get(0));
		} else {
			result = stringArray.get(0);
		}
		return result;
	}
	
	private static String getPartialString(String value) throws TestException {
		String result = null;
		try {
    		int length = getRandomInt(value.length(), false);
    		if (length == 0) {
    			result = value;
    		} else {
    			result = value.substring(0, length);
    		}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	private static String getRandomStringFromList(RandomStringBuilder builder) throws TestException {
		String result = null;
		Collections.shuffle(builder.stringList);
		if (builder.isPartialString) {
			result = getPartialString(builder.stringList.get(0));
		} else {
			result = builder.stringList.get(0);
		}
		return result;
	}
	
	private String createRandomStringFromCharacters(RandomStringBuilder builder) throws TestException {
		StringBuilder sb = new StringBuilder(builder.maxLength);
		try {
			SecureRandom rnd = new SecureRandom();
			if (builder.returnsAtRandomLength) {
				int randomLength = getRandomInt(builder.maxLength, true);
				for (int i = 0; i < randomLength; i++) {
					sb.append(builder.stringValue.charAt(rnd.nextInt(builder.stringValue.length())));
				}
			} else {
				for (int i = 0; i < builder.maxLength; i++) {
					sb.append(builder.stringValue.charAt(rnd.nextInt(builder.stringValue.length())));
				}
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
		return sb.toString();
	}
	
	private static int getRandomInt(int length, Boolean noZero) throws TestException {
		int result = 0;
		try {
    		Random random = new Random();
    		result = random.nextInt(length);
    		if (noZero) {
    			while (result == 0) {
    				result = result+1;
    			}
    		}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public String get() {
		return result;
	}

	public static class RandomStringBuilder {

		// optional params
		private String[] stringArray;
		private List<String> stringList;
		private String stringValue;
		private int maxLength;
		private Boolean returnsAtRandomLength;
		private Boolean isPartialString;

		/**
		 * <p> This creates an instance of RandomStringBuilder which utilizes
		 * a builder pattern to assign values to important variables used in
		 * the RandomStringHelper class.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code String[] stringArray = new String[] { "Test1", "Test2", "Test3" };
		 *  String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(stringArray))
		 *			.get();}
		 * </pre>
		 * @param info - the text that will be written to the test results report
		 */
		public RandomStringBuilder() {
			if (returnsAtRandomLength == null) {
				returnsAtRandomLength = false;
			}
			if (isPartialString == null) {
				isPartialString = false;
			}
		}

		/**
		 * <p>Tells the RandomStringHelper which array needs
		 * to be used for a random selection.</p>
	 	 * <pre>
		 * Ex: 
		 * {@code String[] stringArray = new String[] { "Test1", "Test2", "Test3" };
		 *  String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(stringArray))
		 *			.get();}
		 * </pre>
		 * @param stringArray - the string array to be used
		 */
		public RandomStringBuilder buildAString(String[] stringArray) {
			this.stringArray = stringArray;
			return this;
		}
		
		/**
		 * <p>Tells the RandomStringHelper which list needs
		 * to be used for a random selection.</p>
	 	 * <pre>
		 * Ex: 
		 * {@code List<String> stringArray = new ArrayList<>();
		 *	stringArray.add("Test1");
		 *	stringArray.add("Test2"); 
		 *	stringArray.add("Test3");
		 * String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(stringArray))
		 *			.get();}
		 * </pre>
		 * @param stringArray - the list to be used
		 */
		public RandomStringBuilder buildAString(List<String> stringList) {
			this.stringList = stringList;
			return this;
		}

		/**
		 * <p>Tells the RandomStringHelper which String will
		 * be scrambled into a different randomized string.</p>
	 	 * <pre>
		 * Ex: 
		 * {@code String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		 *  String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(characters)
		 *			.withAMaxLengthOf(5))
		 *			.get();}
		 * </pre>
		 * @param stringArray - the string array to be used
		 */
		public RandomStringBuilder buildAString(String stringValue) {
			this.stringValue = stringValue;
			return this;
		}

		/**
		 * <p>Tells the RandomStringHelper what the max length
		 * of the new randomized string should be.</p>
		 * <p>This is only required when taking a string of characters
		 * and converting into a newly randomized string. It is not required 
		 * for any of the other scenarios.</p>
	 	 * <pre>
		 * Ex: 
		 * {@code String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		 *  String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(characters)
		 *			.withAMaxLengthOf(5))
		 *			.get();}
		 * </pre>
		 * @param stringArray - the string array to be used
		 */
		public RandomStringBuilder withAMaxLengthOf(int maxLength) {
			this.maxLength = maxLength;
			return this;
		}

		/**
		 * <p>Tells the RandomStringHelper whether or not to return
		 * a value at a randomized length between 0 and the specified max length.</p>
		 * <p>This is not a required variable. It is only set when a random length
		 * between 0 and the max length is necessary. The default value is false.</p>
	 	 * <pre>
		 * Ex: 
		 * {@code String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		 *  String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(characters)
		 *			.withAMaxLengthOf(5)
		 *			.returnsAStringOfRandomLength(true))
		 *			.get();}
		 * </pre>
		 * @param stringArray - the string array to be used
		 */
		public RandomStringBuilder returnsAStringOfRandomLength(Boolean returnsAtRandomLength) {
			this.returnsAtRandomLength = returnsAtRandomLength;
			return this;
		}
		
		/**
		 * <p>Tells the RandomStringHelper whether or not to return
		 * a partial string value. A partial string will always return
		 * at a random length between 1 and the total number of characters 
		 * in the string.</p>
		 * <p>This is not a required variable. It is only set when a partial string value
		 * is necessary. The default value is false.</p>
		 * <p>It should be noted that getting a partial string can apply to 
		 * any of the different helper functionalities. You can get a partial 
		 * from a random value from an array, or string, or string array, etc.</p>
	 	 * <pre>
		 * Ex: 
		 * {@code String characters = "TestingTime";
		 *  String test = new RandomStringHelper(new RandomStringBuilder()
		 *			.buildAString(characters)
		 *			.returnAPartialString(true))
		 *			.get();}
		 * </pre>
		 * @param stringArray - the string array to be used
		 */
		public RandomStringBuilder returnAPartialString(Boolean value) {
			this.isPartialString = value;
			return this;
		}

	}

}
