package com.warnermedia.data.loki;

import java.util.Random;

public class RandomService {
	private static final Random SHARED_RANDOM = new Random();
	private final Random random;

	/**
	 * @param random
	 *            If null is passed in, a default
	 *            Random is assigned
	 */
	public RandomService(Random random) {
		this.random = random != null ? random : SHARED_RANDOM;
	}

	public int nextInt(int n) {
		return random.nextInt(n);
	}
}
