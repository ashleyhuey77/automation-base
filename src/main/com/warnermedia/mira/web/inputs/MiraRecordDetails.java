package com.warnermedia.mira.web.inputs;

import com.warnermedia.page.core.web.Type;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import org.openqa.selenium.support.How;

public enum MiraRecordDetails implements Type {

    RECORD_ID {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("body > form > table:nth-child(30) > tbody > tr:nth-child(1) > td:nth-child(8) > span.FORM_FLD_DATA"), new By(How.CSS));
        }
    },
}
