package com.page.core;

import com.config.TestException;
import com.page.utils.*;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class PageUtils {

    public ClickHelper click() throws TestException {
        return new ClickHelper();
    }

    public EnterTextHelper enter() throws TestException {
        return new EnterTextHelper();
    }

    public OptionSelector selector() throws TestException {
        return new OptionSelector();
    }

    public DropdownHelper dropdown() throws TestException {
        return new DropdownHelper();
    }

    public VerifyTextHelper verify() throws TestException {
        return new VerifyTextHelper();
    }

    public ElementHelper find(List<WebElement> elementList) throws TestException {
        return new ElementHelper(elementList);
    }
}
