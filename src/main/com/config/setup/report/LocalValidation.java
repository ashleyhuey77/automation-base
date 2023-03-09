package com.config.setup.report;

/**
 * <h2>LocalValidation</h2>
 * <p>The LocalValidation class is used to get and set the threadsafe instance of 
 * ValidationsHelper.</p>
 * @author ashleyhuey
 *
 */
public class LocalValidation {
	
	private LocalValidation() {
		
	}

    private static ThreadLocal < ValidationsHelper > validations = new ThreadLocal <> ();

    /**
     * <p>Get the threadsafe instance of ValidationsHelper.</p>
     * @return ValidationsHelper
     */
    public static ValidationsHelper getValidations() {
        return validations.get();
    }

    /**
     * <p>Set the threadsafe instance of ValidationsHelper </p>
     * @param value - the ValidationsHelper instance to set.
     */
    public static void setValidations(ValidationsHelper invalidations) {
        validations.set(invalidations);
    }

}