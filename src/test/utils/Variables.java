package utils;

import com.page.core.web.Type;
import com.selenium.By;
import com.selenium.Locator;
import com.selenium.TestElement;
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
