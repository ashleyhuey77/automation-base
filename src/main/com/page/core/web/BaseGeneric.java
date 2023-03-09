package com.page.core.web;

import com.selenium.By;
import com.selenium.Locator;
import com.selenium.TestElement;
import org.openqa.selenium.support.How;


public enum BaseGeneric implements Type {

	USER_NAME_TEXT_FIELD {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#credentials-username"), new By(How.CSS));
		}
	},
	USER_NAME_TEXT_FIELD2 {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#idp-discovery-username"), new By(How.CSS));
		}
	},
	USER_NAME_TEXT_FIELD3 {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#okta-signin-username"), new By(How.CSS));
		}
	},
	NEXT_BUTTON {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#idp-discovery-submit"), new By(How.CSS));
		}
	},
	LOG_IN_BOX {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#topbarLoggedoutBox[class='qrk-topbar modal fade in']"), new By(How.CSS));
		}
	},
	PWD_TEXT_FIELD {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#credentials-password"), new By(How.CSS));
		}
	},
	PWD_TEXT_FIELD2 {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#okta-signin-password"), new By(How.CSS));
		}
	},
	ANYWHERE_PWD_TEXT_FIELD {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#anywhere-credentials-password"), new By(How.CSS));
		}
	},
	ANYWHERE_SIGN_IN_BTN {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#anywhere-credentials-signInButton"), new By(How.CSS));
		}
	},
	SIGN_IN_BTN {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#signInBox button[class='btn qrk-btn-action']"), new By(How.CSS));
		}
	},
	USER {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#topbar-user"), new By(How.CSS));
		}
	},
	LOG_IN_BTN {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#okta-signin-submit"), new By(How.CSS));
		}
	},
	ERROR_MSG {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("credentials-loginError"), new By(How.ID));
		}
	},
	ERROR_TEXT {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("//*[@id='credentials-loginError']/text()[1]"), new By(How.XPATH));
		}
	},
	DASHBOARD_LOAD_ELEMENT {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("div[class='dashboard-app']"), new By(How.CSS));
		}
	},
	EA_SIGNIN_BOX {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#anywhereLoginBox[style='display: block;']"), new By(How.CSS));
		}
	},
	NOTIFICATIONS_BUTTON {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("topbar-notification-container"), new By(How.ID));
		}
	},
	ALL_APPS_LINK {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("All Apps"), new By(How.LINK_TEXT));
		}
	},
	NEWS_APPS_TOGGLE {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("div[class='btn menu-toggle NewsApps']"), new By(How.CSS));
		}
	},
	CORE_APPS_TOGGLE {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("div[class='btn menu-toggle CoreApps']"), new By(How.CSS));
		}
	},
	TOP_NAV_BAR {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("#topbar-appList"), new By(How.CSS));
		}
	},
	NOTIFICATION_CONTENT {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("notificationContent"), new By(How.ID));
		}
	},
	DISMISS_BUTTON {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("modalActionDismiss"), new By(How.ID));
		}
	},
	MODAL_BOX {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("modalBox"), new By(How.ID));
		}
	},
	MODAL_TITLE {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("modalTitle"), new By(How.ID));
		}
	},
	MODAL_MESSAGE {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("modalMessage"), new By(How.ID));
		}
	},
	SEQUENCE_CATEGORY {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("td.category"), new By(How.CSS));
		}
	},
	TOP_CONTAINER_VALUES {
		@Override
		public TestElement element() {
			return new TestElement(new Locator("div[class='top-container'] div[class='video-details-view'] div[class='bottom-info-value']"), new By(How.CSS));
		}
	};

}
