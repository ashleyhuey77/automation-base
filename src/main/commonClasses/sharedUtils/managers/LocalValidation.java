package commonClasses.sharedUtils.managers;

import commonClasses.sharedUtils.helpers.ValidationsHelper;

public class LocalValidation {
	
	private static ThreadLocal<ValidationsHelper> validations = new ThreadLocal<ValidationsHelper>();
	 
    public static ValidationsHelper getValidations() {
    	return validations.get();
    }
    
    public static void setValidations(ValidationsHelper invalidations)
    {
    	validations.set(invalidations);
    }

}
