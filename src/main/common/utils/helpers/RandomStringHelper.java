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

	public RandomStringHelper(RandomStringBuilder builder) throws Exception {
		if (builder.stringArray != null && builder.stringArray.length > 0) {
			result = getRandomStringFromArray(builder);
		} else if (!TestUtils.isNullOrBlank(builder.stringValue)) {
			result = createRandomStringFromCharacters(builder);
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
	public static String getRandomStringFromArray(RandomStringBuilder builder) {
		String result = null;
		List<String> stringArray = new ArrayList<>();
		for (String i : builder.stringArray) {
			stringArray.add(i);
		}
		Collections.shuffle(stringArray);
		result = stringArray.get(0);
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
				Random random = new Random();
				int randomLength = random.nextInt(builder.maxLength);
				if (randomLength == 0) {
					randomLength++;
				}
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

	public String get() {
		return result;
	}

	public static class RandomStringBuilder {

		// optional params
		private String[] stringArray;
		private String stringValue;
		private int maxLength;
		private Boolean returnsAtRandomLength;

		public RandomStringBuilder() {
			if (returnsAtRandomLength == null) {
				returnsAtRandomLength = false;
			}
		}

		public RandomStringBuilder buildAString(String[] stringArray) {
			this.stringArray = stringArray;
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

	}

}
