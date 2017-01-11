package commonClasses.sharedUtils;

public class ExtensionMethods {

	public static boolean isNullOrBlank(String param) { 
	    return param == null || param.trim().length() == 0;
	}
	
	public static float getTotalPassNumber(float totalFails, float totalTestCases)
	{
		if (totalFails != 0)
		{
			float result = totalTestCases - totalFails;
			return result;
		}
		else
		{
			return totalTestCases;
		}
	}
	
	public static float getThePassPercentage(float totalPassNumber, float totalTestCases)
	{
		if (totalPassNumber != totalTestCases)
		{
			float passTimes100 = totalPassNumber * 100;
			float percentageValue = passTimes100 / totalTestCases;
			return percentageValue;
		}
		else
		{
			return 100;
		}
	}
		
}
