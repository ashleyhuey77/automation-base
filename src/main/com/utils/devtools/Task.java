package com.utils.devtools;

import java.util.Optional;

/**
 * Template-method class for callback hook execution.
 */
public abstract class Task<V> {

	V className;

	/**
	 * Execute with callback.
	 */
	public final void executeWith(Callback callback) {
		execute();
		Optional.ofNullable(callback).ifPresent(Callback::call);
	}

	public abstract void execute();
}
