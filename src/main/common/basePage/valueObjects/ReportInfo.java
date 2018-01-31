package common.basePage.valueObjects;

public class ReportInfo {
	
		String elementTitle;
		
		public ReportInfo(String value) {
			elementTitle = value;
		}
		
		public String elementTitle() {
			return elementTitle;
		}
}
