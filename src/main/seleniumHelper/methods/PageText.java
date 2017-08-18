package seleniumHelper.methods;

import org.openqa.selenium.WebElement;

import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IText;

public class PageText extends Commands implements IText {

    @Override
    public String getFrom(String selectorString, String by, String...attribute) throws Exception {
        return getElement(selectorString, by).getText().trim();
    }

    @Override
    public String getFrom(WebElement element, String...attribute) throws Exception {
        return element.getText().trim();
    }

    @Override
    public Boolean isDisplayed(String selectorString, String by, String expectedText, String...attribute) throws Exception {
        try {
            String actualText = getElement(selectorString, by).getText();
            String modifiedActualText = actualText.replace("\r\n", " ");
            if (modifiedActualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Boolean isDisplayed(WebElement element, String expectedText, String...attribute) throws Exception {
        try {
            String actualText = element.getText();
            String modifiedActualText = actualText.replace("\r\n", " ");
            if (modifiedActualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

}