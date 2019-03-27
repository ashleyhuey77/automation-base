package common.utils.helpers;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import common.utils.TestUtils;
import common.utils.managers.LocalReport;
import log.TestException;

public class RandomStringHelper {

	private String result;

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

	/**
	 * <p>
	 * This method takes an array of strings and
	 * selects a random string from the array.
	 * </p>
	 * 
	 * @param strings
	 *            - the array of strings that a string
	 *            value should be randomly selected
	 *            from
	 * @return String
	 * @throws TestException
	 */
	public static String getRandomStringFromArray(RandomStringBuilder builder) throws TestException {
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
	
	/**
	 * <p>
	 * This method takes an array of strings and
	 * selects a random string from the array.
	 * </p>
	 * 
	 * @param strings
	 *            - the array of strings that a string
	 *            value should be randomly selected
	 *            from
	 * @return String
	 * @throws TestException
	 */
	public static String getRandomStringFromList(RandomStringBuilder builder) throws TestException {
		String result = null;
		Collections.shuffle(builder.stringList);
		if (builder.isPartialString) {
			result = getPartialString(builder.stringList.get(0));
		} else {
			result = builder.stringList.get(0);
		}
		return result;
	}

	/**
	 * <p>
	 * Method to generate a random string of text by
	 * taking a string of text and scrambling it into
	 * a new string at a randomly selected length that
	 * will not surpass the specified length param.
	 * </p>
	 * 
	 * @param length
	 *            - the total length that the random
	 *            string of text should be when
	 *            completed. This will randomly select
	 *            a length with the number specified
	 *            in this param being the highest
	 *            length possible.
	 * @param value
	 *            - the String of characters that will
	 *            be used to create the random string
	 *            of text. Method takes the the
	 *            characters in this string and
	 *            scrambles them to generte the random
	 *            string.
	 * @return String
	 * @throws Exception 
	 */
	protected String createRandomStringFromCharacters(RandomStringBuilder builder) throws TestException {
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

		public RandomStringBuilder() {
			if (returnsAtRandomLength == null) {
				returnsAtRandomLength = false;
			}
			if (isPartialString == null) {
				isPartialString = false;
			}
		}

		public RandomStringBuilder buildAString(String[] stringArray) {
			this.stringArray = stringArray;
			return this;
		}
		
		public RandomStringBuilder buildAString(List<String> stringList) {
			this.stringList = stringList;
			return this;
		}

		public RandomStringBuilder buildAString(String stringValue) {
			this.stringValue = stringValue;
			return this;
		}

		public RandomStringBuilder withAMaxLengthOf(int maxLength) {
			this.maxLength = maxLength;
			return this;
		}

		public RandomStringBuilder returnsAStringOfRandomLength(Boolean returnsAtRandomLength) {
			this.returnsAtRandomLength = returnsAtRandomLength;
			return this;
		}
		
		public RandomStringBuilder returnAPartialString(Boolean value) {
			this.isPartialString = value;
			return this;
		}

	}

}
