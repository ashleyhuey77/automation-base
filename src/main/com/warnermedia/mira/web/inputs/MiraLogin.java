package com.warnermedia.mira.web.inputs;

import com.warnermedia.page.core.web.Type;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import org.openqa.selenium.support.How;

public enum MiraLogin implements Type {

    USER_NAME {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[name='txt_Username']"), new By(How.CSS));
        }
    },
    PASSWORD {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[name='txt_Password']"), new By(How.CSS));
        }
    },
    LOGIN_BUTTON {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[name='Login_SUB']"), new By(How.CSS));
        }
    },
}
