package tests;

import org.testng.TestException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.utils.TestReport;
import common.utils.WebDriverListener;
import common.utils.builders.Environment;
import common.utils.enums.ReportType;
import common.utils.facades.HelperFacade;
import common.utils.helpers.TestReportHelper;
import common.utils.managers.LocalDriver;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalTest;
import common.utils.managers.LocalValidation;
import log.Log;
import pages.TestInitialization;

@Listeners(WebDriverListener.class)
public class Test1  extends TestInitialization {
	
	@BeforeMethod
	public void beforeScenario() throws log.TestException {
		Log.set();
		Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
		LocalTest.setEnvironment(environment);
		TestReportHelper helper = new TestReportHelper();
		TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
		HelperFacade.initializeReportType(ReportType.REPORT, report);
		HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
		LocalDriver.getDriver().get("http://www.facebook.com");
	}
	
	@Test
	public void test1() throws log.TestException {
		try {
			LocalValidation.getValidations().assertionPass("Test 1");
			LocalValidation.getValidations().assertionPass("Test 2");
			LocalValidation.getValidations().assertionPass("Test 3");
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}
	
	@Test
	public void test2() {
		System.out.println("Test2 has been executed.");
	}
	
/*	@Test
	public void test3() {
		System.out.println("Test3 has been executed.");
	}
	
	@Test
	public void test4() {
		System.out.println("Test4 has been executed.");
	}
	
	@Test
	public void test5() {
		System.out.println("Test5 has been executed.");
	}
	
	@Test
	public void test6() {
		System.out.println("Test6 has been executed.");
	}
	
	@Test
	public void test7() {
		System.out.println("Test7 has been executed.");
	}
	
	@Test
	public void test8() {
		System.out.println("Test8 has been executed.");
	}*/

}
