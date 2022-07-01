package com.warnermedia.mira.web.inputs;

import com.warnermedia.page.core.web.Type;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import org.openqa.selenium.support.How;

public enum MiraServerFulfillment implements Type {

    REQUEST_ROWS {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("table tbody tr"), new By(How.CSS));
        }
    },
   REFRSH_BTN {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("#refresh"), new By(How.CSS));
        }
    },
}
