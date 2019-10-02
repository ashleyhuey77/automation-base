package com.warnermedia.page.utils;

public class ReportInfo {
	
		String elementTitle;
		
		/**
		 * <p> The value that needs to be recorded to the
		 * report. Typically provided in title form.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code 	"Submit Button"
		 * 	-OR-
		 * 	Generic.SUBMIT_BUTTON.name()}
		 * </pre>
		 * @param value - the value to be reported
		 */
		public ReportInfo(String value) {
			elementTitle = value;
		}
		
		/**
		 * <p> Returns the element title </p>
		 * @return String
		 */
		public String elementTitle() {
			return elementTitle;
		}
}
