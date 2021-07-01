package utils;

import com.warnermedia.page.core.web.Type;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import org.openqa.selenium.support.How;

public enum Variables implements Type {

    INPUT_ID_TEST {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[id='Test']"), new By(How.CSS));
        }
    },
    DIV_ID_TEST {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("div[id='Test']"), new By(How.CSS));
        }
    }

}
