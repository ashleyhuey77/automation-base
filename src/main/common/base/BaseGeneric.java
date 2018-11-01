package common.base;

import shelper.vobjects.By;
import shelper.vobjects.Locator;
import shelper.vobjects.TestElement;
import org.openqa.selenium.support.How;


public enum BaseGeneric implements Type {
	
	USER_NAME_TEXT_FIELD(new Locator("#credentials-username"), new By(How.CSS)),
	LOG_IN_BOX(new Locator("div[class='qrk-topbar modal fade in']"), new By(How.CSS)),
	PWD_TEXT_FIELD(new Locator("#credentials-password"), new By(How.CSS)),
	SIGN_IN_BTN(new Locator("#topbarLoggedoutBox > div > div > div.qrk-topbar.modal-footer > button"), new By(How.CSS)),
	ERROR_MSG(new Locator("credentials-loginError"), new By(How.ID)),
	ERROR_TEXT(new Locator("//*[@id='credentials-loginError']/text()[1]"), new By(How.XPATH)),
	DASHBOARD_LOAD_ELEMENT(new Locator("div[class='dashboard-app']"), new By(How.CSS));
	
	private final Locator locator;
	private final By by;
	private final TestElement element;
	
	private BaseGeneric(Locator locator, By by) {
		this.locator = locator;
		this.by = by;
		element = new TestElement(locator, by);
	}
	
	@Override
	public Locator locator() {
		return locator;
	}
	
	@Override
	public By by() {
		return by;
	}
	
	@Override
	public TestElement element() {
		return element;
	}

}
