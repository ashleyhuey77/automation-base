package seleniumHelper.methods;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.enums.SelectType;
import seleniumHelper.enums.Wait;
import seleniumHelper.interfaces.IActions;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class ElementActions extends Commands implements IActions {

	@Override
    public void moveTo(Locator locator, By by) throws Exception {
        try {
            Actions actions = new Actions(LocalDriver.getDriver());
            actions.moveToElement(getElement(locator, by)).perform();
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void moveTo(WebElement element) throws Exception {
        try {
            Actions actions = new Actions(LocalDriver.getDriver());
            actions.moveToElement(element).perform();
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void mouseOver(Locator locator, By by) throws Exception {
        try {
            Actions actions = new Actions(LocalDriver.getDriver());
            actions.clickAndHold(getElement(locator, by)).perform();
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void mouseOver(WebElement element) throws Exception {
        try {
            Actions actions = new Actions(LocalDriver.getDriver());
            actions.clickAndHold(element).perform();
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void dragAndDrop(WebElement firstElement, WebElement secondElement, String firstElementlocator,
        String index, String stepWidth, String stepDelay, String dx, String dy) throws Exception {
        try {
            WebElement LocatorFrom = firstElement;
            int xFrom = LocatorFrom.getLocation().x;
            int yFrom = LocatorFrom.getLocation().y;
            int[] difference = {
                0,
                0
            };
            addDragNDropScriptsToPage();

            if (dx == null && dy == null) {
                WebElement LocatorTo = secondElement;
                int xto = LocatorTo.getLocation().x;
                int yto = LocatorTo.getLocation().y;
                ArrayList < String > values = dXAnddYValues(xto, yto, xFrom, yFrom, LocatorTo.getSize().height,
                    LocatorFrom.getSize().height, difference);

                executeDragNDrop(firstElementlocator, values.get(0), values.get(1), stepWidth, stepDelay, index);
            } else {
                executeDragNDrop(firstElementlocator, dx, dy, stepWidth, stepDelay, index);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void dragAndDrop(Locator firstElementlocator, By firstElementBy,
        Locator secondElementlocator, By secondElementBy, String stepWidth, String stepDelay, String dx,
        String dy) throws Exception {
        try {
            WebElement LocatorFrom = getElement(firstElementlocator, firstElementBy);
            int xFrom = LocatorFrom.getLocation().x;
            int yFrom = LocatorFrom.getLocation().y;
            int[] difference = {
                0,
                0
            };
            addDragNDropScriptsToPage();

            if (dx == null && dy == null) {
                WebElement LocatorTo = getElement(secondElementlocator, secondElementBy);
                int xto = LocatorTo.getLocation().x;
                int yto = LocatorTo.getLocation().y;
                ArrayList < String > values = dXAnddYValues(xto, yto, xFrom, yFrom, LocatorTo.getSize().height,
                    LocatorFrom.getSize().height, difference);

                executeDragNDrop(firstElementlocator.value(), values.get(0), values.get(1), stepWidth, stepDelay);
            } else {
                executeDragNDrop(firstElementlocator.value(), dx, dy, stepWidth, stepDelay);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void dragAndDrop(Locator dragable, By dragableby, Locator dropable, By dropableby, int timeTowait)
    throws Exception {

        try {
            WebElement dragElement = null;
            WebElement dropElement = null;

            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(dragable, dragableby);
            // List<WebElement> locate = getElements(dragable, dragableby);
            List < WebElement > locate = SHelper.get().element().getListOf(dragable, dragableby);
            dragElement = locate.get(0);
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(timeTowait)).on(dragable, dragableby);
            dragElement = locate.get(0);
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(dragable, dragableby);
            // List<WebElement> locateDroppable =
            // getElements(dropable,dropableby);
            List < WebElement > locateDroppable = SHelper.get().element().getListOf(dropable, dropableby);
            Actions act = new Actions(LocalDriver.getDriver());
            dropElement = locateDroppable.get(0);
            // SHelper.get().click(Via.JQUERY).on(dragElement);
            act.clickAndHold(dragElement).build().perform();
            Thread.sleep(5000);
            act.moveToElement(dropElement).build().perform();
            Thread.sleep(5000);
            act.release();
            act.release(dragElement).build().perform();
            Thread.sleep(5000);
            System.out.print(dragElement.getText() + "and dropped was: " + dropElement.getText());
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void scrollTo(Locator locator, By by) throws Exception {
        try {
            WebElement element = getElement(locator, by);
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void scrollTo(WebElement element) throws Exception {
        try {
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    /**
     * <summary> Method to execute the drag and drop javascript command
     * </summary>
     * 
     * @param from
     *            the selector string of the "from" element
     * @param dx
     * @param dy
     * @param stepWidth
     * @param stepDelay
     * @throws Exception
     */
    private void executeDragNDrop(String from, String dx, String dy, String stepWidth, String stepDelay)
    throws Exception {
        try {
            ((JavascriptExecutor) LocalDriver.getDriver())
            .executeScript("$('" + from + "').simulate('drag-n-drop', {dx: " + dx + ", dy: " + dy +
                ", interpolation: {stepWidth: " + stepWidth + ", stepDelay: " + stepDelay + "}})");
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * <summary> Method to execute the drag and drop javascript command
     * </summary>
     * 
     * @param from
     *            the selector string of the "from" element
     * @param dx
     * @param dy
     * @param stepWidth
     * @param stepDelay
     * @param index
     *            the index of the element to be selected from a list of similar
     *            elements
     * @throws Exception
     */
    private void executeDragNDrop(String from, String dx, String dy, String stepWidth, String stepDelay, String index)
    throws Exception {
        try {
            ((JavascriptExecutor) LocalDriver.getDriver())
            .executeScript("$('" + from + "')[" + index + "].simulate('drag-n-drop', {dx: " + dx + ", dy: " + dy +
                ", interpolation: {stepWidth: " + stepWidth + ", stepDelay: " + stepDelay + "}})");
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * <summary> Method to inject the drag and drop scripts into the page so
     * that the drag and drop functionality can be used. </summary>
     * 
     * @return void
     */
    private void addDragNDropScriptsToPage() throws Exception {
        addScriptToPage("http://j-ulrich.github.io/jquery-simulate-ext/jquery.simulate.js");
        Thread.sleep(500);
        addScriptToPage("http://j-ulrich.github.io/jquery-simulate-ext/jquery.simulate.ext.js");
        Thread.sleep(500);
        addScriptToPage("http://j-ulrich.github.io/jquery-simulate-ext/jquery.simulate.drag-n-drop.js");
        Thread.sleep(500);
    }

    /**
     * <summary> Method to inject a script to a webpage </summary>
     * 
     * @param script
     *            the script that needs to be injected
     * @return void
     * @throws Exception
     */
    private void addScriptToPage(String script) throws Exception {
        try {
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$.getScript('" + script + "')");
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * <summary> Method to get the dx and dy values and store them in an
     * arrayList </summary>
     * 
     * @param x1
     *            the first element's x value
     * @param y1
     *            the second element's y value
     * @param x2
     *            the second element's x value
     * @param y2
     *            the second element' y value
     * @param y1Height
     *            the first element's height
     * @param y2Height
     *            the second element's height
     * @param difference
     * @return ArrayList<\String>
     * @throws Exception
     */
    private ArrayList < String > dXAnddYValues(int x1, int y1, int x2, int y2, int y1Height, int y2Height,
        int[] difference) throws Exception {
        ArrayList < String > values = new ArrayList < > ();
        y1 = y1 + (y1Height / 2);
        x2 = x2 - difference[0];
        y2 = y2 - difference[1];
        y2 = y2 + (y2Height / 2);
        int dx = x2 - x1;
        int dy = y2 - y1;

        values.add(Integer.toString(dx));
        values.add(Integer.toString(dy));
        return values;
    }
    /**
     * <summary> method for selecting from dropdown</summary>
     * @param element - the DOM element from dropdown
     * @param value - the value to select from dropdown
     * @param selectType - the selection type eithe by value, by visible text or by index
     * @author OJ
     */
    @Override
    public void selectFromDropDown(WebElement element, String value, SelectType selectType) throws Exception {
        try {
            Select sel = new Select(element);
            selectOption(sel, value, selectType);
        } catch (Exception ex) {
            throw ex;
        }    
    }
    /**
     * <summary> method for selecting from dropdown</summary>
     * @param locator- the DOM element from dropdown
     * @param value - the value to select from dropdown
     * @param by - the css selector
     * @param selectType - the selection type eithe by value, by visible text or by index
     * @author OJ
     */
    @Override
    public void selectFromDropDown(Locator locator, By by, String value, SelectType selectType)
    throws Exception {
        try {
            Select sel = new Select(getElement(locator, by));
            selectOption(sel, value, selectType);
        } catch (Exception e) {
            throw e;
        }
    }
/**
 * <summary>Make the selection</summary>
 * @param select
 * @param value
 * @param selectType
 * @throws Exception
 */
    private void selectOption(Select select, String value, SelectType selectType) throws Exception {
        try {
            switch (selectType) {
                case byValue:
                    select.selectByValue(value);
                    break;
                case byVisibleText:
                    select.selectByVisibleText(value);
                    break;
                case byIndex:
                    select.selectByIndex(1);
                default:
                    System.out.println("A selection must be made");
                    break;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

@Override
public void dragAndDrop(WebElement elementToBeDragged, WebElement elementToBeDropped, int timeTowait) throws Exception {
	try {
        WebElement dragElement = null;
        WebElement dropElement = null;

        SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(timeTowait)).on(elementToBeDragged);
        dragElement = elementToBeDragged;
        SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(elementToBeDragged);
        Actions act = new Actions(LocalDriver.getDriver());
        dropElement = elementToBeDropped;
        act.clickAndHold(dragElement).build().perform();
        Thread.sleep(5000);
        act.moveToElement(dropElement).build().perform();
        Thread.sleep(5000);
        act.release();
        act.release(dragElement).build().perform();
        Thread.sleep(5000);
        System.out.print(dragElement.getText() + "and dropped was: " + dropElement.getText());
    } catch (WebDriverException ex) {
        throw ex;
    }
}

}