package shelper.builders;

import shelper.enums.Condition;

public class WaitBuilder {
	
	public int baseTime = 0;
	public Condition baseCondition;
	public String baseValue;
	public int baseExpectedTotalCount = 0;
	public String baseAttribute;
	public String indexOf;
	
	/**
	 * The WaitBuilder builds the params and conditions
	 * in order for a wait to be executed. Certain waits require
	 * certain params in order to function correctly. The WaitBuilder
	 * sets those params in a way that creates a readable sentence that
	 * accurately conveys what the wait is and how it will function.
	 */
	public WaitBuilder() {
		/**
		 * Constructor
		 */
	}
	
	/**
	 * <p>This sets the total count variable for a wait. 
	 * This variable should be to total number of a particular
	 * element that is expected to be on the page.</p>
	 */
	public WaitBuilder withACountOf(int value) {
		this.baseExpectedTotalCount = value;
		return this;
	}
	
	/**
	 * <p>This sets the max wait time. This variable should be
	 * the total amount of time a condition should run before it fails.</p>
	 */
	public WaitBuilder forAMaxTimeOf(int value) {
		this.baseTime = value;
		return this;
	}
	
	/**
	 * <p>This sets the attribute variable. This variable
	 * should be set when waiting on an attribute. It should
	 * contain the title of the attribute that is being waited on
	 * i.e (style, class, id, etc.)</p>
	 */
	public WaitBuilder forAttribute(String value) {
		this.baseAttribute = value;
		return this;
	}
	
	/**
	 * <p>This sets the Condition enum variable. This variable 
	 * should be set when waiting on a text to either CONTAIN
	 * or EQUAL a particular value.</p>
	 */
	public WaitBuilder to(Condition value) {
		this.baseCondition = value;
		return this;
	}
	
	/**
	 * <p>This sets the value variable. This variable should
	 * be set when waiting on a text value to be present. It represents
	 * the text value that is to be waited on.</p>
	 */
	public WaitBuilder value(String value) {
		this.baseValue = value;
		return this;
	}
	
	/**
	 * <p>This sets the index variable. This variable should
	 * be set when there is a list of webelements that only
	 * needs one item in the list to be waited on. The index 
	 * selects that item in the list.</p>
	 */
	public WaitBuilder indexOf(String value) {
		this.indexOf = value;
		return this;
	}
	
	public Condition baseCondition() {
		if (this.baseCondition == null) {
			return Condition.INVALID_CONDITION;
		} else {
			return baseCondition;
		}
	}
	
}
