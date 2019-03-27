package shelper.builders;

import shelper.enums.Condition;

public class WaitBuilder {
	
	public int baseTime = 0;
	public Condition baseCondition;
	public String baseValue;
	public int baseExpectedTotalCount = 0;
	public String baseAttribute;
	public String indexOf;
	
	public WaitBuilder withACountOf(int value) {
		this.baseExpectedTotalCount = value;
		return this;
	}
	
	public WaitBuilder forAMaxTimeOf(int value) {
		this.baseTime = value;
		return this;
	}
	
	public WaitBuilder forAttribute(String value) {
		this.baseAttribute = value;
		return this;
	}
	
	public WaitBuilder to(Condition value) {
		this.baseCondition = value;
		return this;
	}
	
	public WaitBuilder value(String value) {
		this.baseValue = value;
		return this;
	}
	
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
