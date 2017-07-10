package commonClasses.sharedUtils;

public class LocalValidationManager {
	
	private static ThreadLocal<Validations> validations = new ThreadLocal<Validations>();
	 
    public static Validations getValidations() {
    	return validations.get();
    }
    
    public static void setValidations(Validations invalidations)
    {
    	validations.set(invalidations);
    }

}
