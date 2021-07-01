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
            return new TestElement(new Locator("a[onclick*='ServerFulFillment')]"), new By(How.CSS));
        }
    },
}
