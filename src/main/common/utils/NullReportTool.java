package common.utils;

import common.base.vobjects.ReportInfo;
import log.TestException;

public class NullReportTool {
	
	public NullReportTool(NullReportBuilder builder) throws TestException {
        		if (builder.value == null 
        				&& builder.verifyValueIsNotNull) {
        			checkStringFailIfNull(builder);
        		} else if (builder.value != null 
        					&& !builder.verifyValueIsNotNull) {
        			checkStringFailIfNotNull(builder);
        		} else if (builder.number == 0
        					&& builder.verifyIntIsNotZero) {
        			checkNumberFailIfZero(builder);
        		} else if (builder.number != 0
        					&& !builder.verifyIntIsNotZero) {
        			checkNumberFailIfNotZero(builder);
        		} else if (builder.object == null
        					&& builder.verifyObjectIsNotNull) {
        			checkObjectFailIfNull(builder);
        		} else if (builder.object != null
        					&& !builder.verifyObjectIsNotNull) {
        			checkObjectFailIfNotNull(builder);
        		}
	}
	
	private void checkStringFailIfNull(NullReportBuilder builder) throws TestException {
			if (TestUtils.isNullOrBlank(builder.value)) {
				throw new TestException("The " + builder.info.elementTitle() + " was not provided. Please add the appropriate method to the chain.");
			}
	}
	
	private void checkNumberFailIfNotZero(NullReportBuilder builder) throws TestException {
			if (builder.number != 0) {
				throw new TestException("This method's " + builder.info.elementTitle() + " is set to a value that does not require validation. "
						+ "Please recheck the " + builder.info.elementTitle() + " and verify it is set to the correct value or remove the appropriate method from the chain.");
			}
	}
	
	private void checkStringFailIfNotNull(NullReportBuilder builder) throws TestException {
			if (!TestUtils.isNullOrBlank(builder.value)) {
				throw new TestException("This method's " + builder.info.elementTitle() + " is set to a value that does not require validation. "
						+ "Please recheck the " + builder.info.elementTitle() + " and verify it is set to the correct value or remove the appropriate method from the chain.");
			}
	}
	
	private void checkNumberFailIfZero(NullReportBuilder builder) throws TestException {
			if (builder.number == 0) {
				throw new TestException("The " + builder.info.elementTitle() + " was not provided. Please add the appropriate method to the chain.");
			}
	}
	
	private void checkObjectFailIfNull(NullReportBuilder builder) throws TestException {
        		if (builder.object == null) {
        			throw new TestException("The " + builder.info.elementTitle() + " was not provided. Please add the appropriate method to the chain.");
        		}
	}
	
	private void checkObjectFailIfNotNull(NullReportBuilder builder) throws TestException {
        		if (builder.object != null) {
        			throw new TestException("This method's " + builder.info.elementTitle() + " is set to a value that does not require validation. "
    						+ "Please recheck the " + builder.info.elementTitle() + " and verify it is set to the correct value or remove the appropriate method from the chain.");
        		}
	}
	
	public static class NullReportBuilder {
		
		private ReportInfo info;
		
		private String value;
		private int number;
		private Object object;
		private Boolean verifyValueIsNotNull;
		private Boolean verifyIntIsNotZero;
		private Boolean verifyObjectIsNotNull;
		
		public NullReportBuilder(ReportInfo info) {
			this.info = info;
			if (this.verifyValueIsNotNull == null) {
				this.verifyValueIsNotNull = false;
			}
			
			if (this.verifyObjectIsNotNull == null) {
				this.verifyObjectIsNotNull = false;
			}
			
			if (this.verifyIntIsNotZero == null) {
				this.verifyIntIsNotZero = false;
			}
		}
		
		public NullReportBuilder value(String value) {
			this.value = value;
			return this;
		}
		
		public NullReportBuilder object(Object object) {
			this.object = object;
			return this;
		}
		
		public NullReportBuilder number(int value) {
			this.number = value;
			return this;
		}
		
		public NullReportBuilder verifyIntIsNotZero(Boolean value) {
			this.verifyIntIsNotZero = value;
			return this;
		}
		
		public NullReportBuilder verifyValueIsNotNull(Boolean value) {
			this.verifyValueIsNotNull = value;
			return this;
		}
		
		public NullReportBuilder verifyObjectIsNotNull(Boolean answer) {
			this.verifyObjectIsNotNull = answer;
			return this;
		}
	}

}
