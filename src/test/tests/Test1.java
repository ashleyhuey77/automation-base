package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import pages.TestInitialization;

@Listeners(WebDriverListener.class)
public class Test1  extends TestInitialization {
	
	@BeforeMethod
	public void beforeScenario() {
		LocalDriver.getDriver().get("http://www.facebook.com");
	}
	
	@Test
	public void test1() {
		System.out.println("Test1 has been executed.");
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
