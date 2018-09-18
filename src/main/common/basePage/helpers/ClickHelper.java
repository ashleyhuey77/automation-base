package common.basePage.helpers;

import org.openqa.selenium.WebElement;
import common.basePage.valueObjects.ReportInfo;
import common.utils.NullReportTool;
import common.utils.NullReportTool.NullReportBuilder;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalValidation;
import common.utils.managers.SHelper;
import seleniumHelper.enums.Via;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;
import seleniumHelper.valueObjects.TestElement;

public class ClickHelper {

    /**	
     * <p>This s a shared generic method to click an element on the page.</p>
     * <p>The method will catch any exceptions thrown and try to execute/exhaust
     * every method of clicking possible (e.g via selenium, javascript, jquery, etc.)
     * before throwing the exception and failing the test. This is to reduce flimsy 
     * click failures that have been proven to happen infrequently in the NewsApps 
     * application.</p>
     * <p>This method will wait for the click element to display on the 
     * page before attempting to click it. If the element is not 
     * found the test is failed.</p>
     * <p></p>
     * <p></p>
     * @param via - the method by which to click a web element (e.g click via 
     * selenium's built in click functionality, javascript, jquery, etc.)
     * @param locator - the webelement locator string for the field that
     * the text will be entered into
     * @param by - the type of locator being used (i.e id, name, csslocator, xpath, etc.)
     * @param elementBeingTested - the name of the element being tested. This is used for 
     * reporting so that when it is called the report will reflect an
     * element that is unique to the method
     * @param index - this is an optional parameter and should be specified when it is necessary to 
     * click on a web element that is stored in a list of webelements.
     * @throws Exception 
     */
		public ClickHelper(ClickBuilder builder) throws Exception {
				if (builder.element != null) {
					clickSomeTestElement(builder);
				} else if (builder.webElement != null) {
					clickSomeWebElement(builder);
				} else {
					new NullReportTool(new NullReportBuilder(new ReportInfo("TestElement"))
							.object(builder.element)
							.verifyObjectIsNotNull(true));
					new NullReportTool(new NullReportBuilder(new ReportInfo("WebElement"))
							.object(builder.webElement)
							.verifyObjectIsNotNull(true));
				}
		}
		
		private void clickSomeTestElement(ClickBuilder builder) throws Exception {
			try {
				if (SHelper.get().element().isDisplayed(builder.element.locator, builder.element.by, 5)) {
	                tryAllClicks(builder.element.locator, builder.element.by, builder.via, builder.index);
	                LocalReport.getReport().reportDoneEvent(builder.info.elementTitle() + " clicked successfully.");
	            } else {
	                throw LocalValidation.getValidations().assertionFailed("Element is not on the page. Unable to click the " 
	                		+ builder.info.elementTitle());
	            }
			} catch (Exception e) {
				throw LocalReport.getReport().reportException(e);
			}
		}
		
		private void clickSomeWebElement(ClickBuilder builder) throws Exception {
			try {
				if (SHelper.get().element().isDisplayed(builder.webElement, 5)) {
	                SHelper.get().click(builder.via).on(builder.webElement);
	                LocalReport.getReport().reportDoneEvent(builder.info.elementTitle() + " clicked successfully.");
	            } else {
	                throw LocalValidation.getValidations().assertionFailed("Element is not on the page. Unable to click the " 
	                		+ builder.info.elementTitle());
	            }
			} catch (Exception e) {
				throw LocalReport.getReport().reportException(e);
			}
		}
		
		private void tryAllClicks(Locator locator, By by, Via via, int...index) throws Exception {
	        try {
	            indexCheckClick(via, locator, by, index);
	        } catch (Exception ex) {
	            try {
	                indexCheckClick(Via.SELENIUM, locator, by, index);
	            } catch (Exception e2) {
	                try {
	                    indexCheckClick(Via.JAVASCRIPT, locator, by, index);
	                } catch (Exception e3) {
	                    try {
	                        indexCheckClick(Via.JQUERY, locator, by, index);
	                    } catch (Exception e4) {
	                       throw LocalValidation.getValidations().assertionFailed("Test has exhausted all different click "
	                        		+ "methods. Not able to click element with the specified locator.");
	                    }
	                }
	            }
	        }
	    }

	    private void indexCheckClick(Via via, Locator locator, By by, int...index) throws Exception {
	        try {
	            if (index.length > 0) {
	                SHelper.get().click(via).on(locator, by, index[0]);
	            } else {
	                SHelper.get().click(via).on(locator, by);
	            }
	        } catch (Exception e2) {
	            throw e2;
	        }
	    }
		
		public static class ClickBuilder {
			
			//required params
			private ReportInfo info;
			
			//optional params
			private TestElement element;
			private WebElement webElement;
			private Via via;
			private int index;
			
			public ClickBuilder(ReportInfo info) {
				this.info = info;
				if (this.via == null) {
					this.via = Via.SELENIUM;
				}
			}
			
			public ClickBuilder clickOn(TestElement element) {
				this.element = element;
				return this;
			}
			
			public ClickBuilder clickOn(WebElement element) {
				this.webElement = element;
				return this;
			}
			
			public ClickBuilder via(Via via) {
				this.via = via;
				return this;
			}
			
			public ClickBuilder withAnIndexOf(int value) {
				this.index = value;
				return this;
			}
			
		}
}