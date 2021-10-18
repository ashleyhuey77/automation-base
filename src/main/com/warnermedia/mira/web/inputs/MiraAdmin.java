package com.warnermedia.mira.web.inputs;

import com.warnermedia.page.core.web.Type;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import org.openqa.selenium.support.How;

public enum MiraAdmin implements Type {

    RESUBMIT_BTN {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("a[href='ArchiveAdmin.jsp']"), new By(How.CSS));
        }
    },
    DAM_ADMIN_DROPDOWN {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("a[href*='Admin_TBar_DAMA.js'] img"), new By(How.CSS));
        }
    },
    REQUEST_QUEUE_BTN {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("a[href*='DAM']"), new By(How.CSS));
        }
    },
    ADMIN_OMNEON_ARCHIVES_OPT {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("a[href*='resetForMS2']"), new By(How.CSS));
        }
    },
    CNN_ID_FIELD {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("cnnId"), new By(How.ID));
        }
    },
    FORCE_ATL_ARCHIVE_BTN {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("forceATLBtn"), new By(How.ID));
        }
    },
    SUCCESS_MSG {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("span[class='component3']"), new By(How.CSS));
        }
    },
}
