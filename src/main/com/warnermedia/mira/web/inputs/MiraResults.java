package com.warnermedia.mira.web.inputs;

import com.warnermedia.page.core.web.Type;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import org.openqa.selenium.support.How;

public enum MiraResults implements Type {

    RECORDS_FOUND {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("table tr[bgcolor='#C0C0C0'] td font[face='Arial']"), new By(How.CSS));
        }
    },
    RECORD_CHECKBOX {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[name*='checkbox']"), new By(How.CSS));
        }
    },
    SEND_ITEMS_TO_DROPDOWN {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[name*='checkbox']"), new By(How.CSS));
        }
    },
    SELECT_ITEMS_DROPDOWN {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[name*='checkbox']"), new By(How.CSS));
        }
    },
    SUBMIT_BUTTON {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("img[src*='SelSubmit']"), new By(How.CSS));
        }
    },
    PROCESS_BUTTON {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[value='Process']"), new By(How.CSS));
        }
    },
    OK_BUTTON {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[name='okbutton']"), new By(How.CSS));
        }
    },
    RECORD_DETAILS_LINK {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("a[href*='javascript:catalogEditViewNonSel']"), new By(How.CSS));
        }
    },
}
