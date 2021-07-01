package com.warnermedia.mira.web.inputs;

import com.warnermedia.page.core.web.Type;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import org.openqa.selenium.support.How;

public enum MiraSearch implements Type {

    SEARCH_FIELD {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[name='tf_assetid']"), new By(How.CSS));
        }
    },
    COLLECTION_FILTER {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("select[name='ddl_collection']"), new By(How.CSS));
        }
    },
    SEARCH_BUTTON {
        @Override
        public TestElement element() {
            return new TestElement(new Locator("input[name='submit_basicsearch']"), new By(How.CSS));
        }
    },
}
