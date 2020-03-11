package com.warnermedia.page.core.web;

import org.openqa.selenium.support.How;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;


public enum BaseGeneric implements Type {
	
	USER_NAME_TEXT_FIELD(new Locator("#credentials-username"), new By(How.CSS)),
	LOG_IN_BOX(new Locator("#topbarLoggedoutBox[class='qrk-topbar modal fade in']"), new By(How.CSS)),
	PWD_TEXT_FIELD(new Locator("#credentials-password"), new By(How.CSS)),
	ANYWHERE_PWD_TEXT_FIELD(new Locator("#anywhere-credentials-password"), new By(How.CSS)),
	ANYWHERE_SIGN_IN_BTN(new Locator("#anywhere-credentials-signInButton"), new By(How.CSS)),
	SIGN_IN_BTN(new Locator("#topbarLoggedoutBox > div > div > div.qrk-topbar.modal-footer > button"), new By(How.CSS)),
	ERROR_MSG(new Locator("credentials-loginError"), new By(How.ID)),
	ERROR_TEXT(new Locator("//*[@id='credentials-loginError']/text()[1]"), new By(How.XPATH)),
	DASHBOARD_LOAD_ELEMENT(new Locator("div[class='dashboard-app']"), new By(How.CSS)),
	EA_SIGNIN_BOX(new Locator("#anywhereLoginBox[style='display: block;']"), new By(How.CSS)),
	NOTIFICATIONS_BUTTON(new Locator("topbar-notification-container"), new By(How.ID)),
	ALL_APPS_LINK(new Locator("All Apps"), new By(How.LINK_TEXT)),
	NEWS_APPS_TOGGLE(new Locator("div[class='btn menu-toggle NewsApps']"), new By(How.CSS)),
	CORE_APPS_TOGGLE(new Locator("div[class='btn menu-toggle CoreApps']"), new By(How.CSS)),
	TOP_NAV_BAR(new Locator("#topbar-appList"), new By(How.CSS)),
	NOTIFICATION_CONTENT(new Locator("notificationContent"), new By(How.ID)),
	DISMISS_BUTTON(new Locator("modalActionDismiss"), new By(How.ID)),
	MODAL_BOX(new Locator("modalBox"), new By(How.ID)),
	MODAL_TITLE(new Locator("modalTitle"), new By(How.ID)),
	MODAL_MESSAGE(new Locator("modalMessage"), new By(How.ID)),
	SEQUENCE_CATEGORY(new Locator("td.category"), new By(How.CSS)),
	TOP_CONTAINER_VALUES(new Locator("div[class='top-container'] div[class='video-details-view'] div[class='bottom-info-value']"), new By(How.CSS));
	
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
