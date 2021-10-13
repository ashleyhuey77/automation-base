package com.warnermedia.mira.web;

import com.warnermedia.page.core.web.Type;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import org.openqa.selenium.support.How;

public enum MiraGlobalNav implements Type {

    SRVR_FULFILLMENT_TAB {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("ButTab_SrvrFulfill"), new By(How.NAME));
        }
    },
    ADMIN_TAB {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("ButTab_Admin"), new By(How.NAME));
        }
    },
    PARENT {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("./.."), new By(How.XPATH));
        }
    },
}
