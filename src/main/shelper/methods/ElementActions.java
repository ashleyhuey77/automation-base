package shelper.methods;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.enums.SelectType;
import shelper.enums.Wait;
import shelper.interfaces.IActions;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class ElementActions extends Commands implements IActions {

	@Override
	public void moveTo(Locator locator, By by) throws TestException {
		Actions actions = new Actions(LocalDriver.getDriver());
		actions.moveToElement(getElement(locator, by)).perform();
	}

	@Override
	public void moveTo(WebElement element) throws TestException {
		Actions actions = new Actions(LocalDriver.getDriver());
		actions.moveToElement(element).perform();
	}

	@Override
	public void mouseOver(Locator locator, By by) throws TestException {
		Actions actions = new Actions(LocalDriver.getDriver());
		actions.clickAndHold(getElement(locator, by)).perform();
	}

	@Override
	public void mouseOver(WebElement element) throws TestException {
		Actions actions = new Actions(LocalDriver.getDriver());
		actions.clickAndHold(element).perform();
	}

	@Override
	public void dragAndDrop(WebElement firstElement, WebElement secondElement, String firstElementlocator, String index,
			String stepWidth, String stepDelay, String dx, String dy) throws TestException, InterruptedException {
		WebElement locatorFrom = firstElement;
		int xFrom = locatorFrom.getLocation().x;
		int yFrom = locatorFrom.getLocation().y;
		int[] difference = { 0, 0 };
		addDragNDropScriptsToPage();

		if (dx == null && dy == null) {
			WebElement locatorTo = secondElement;
			int xto = locatorTo.getLocation().x;
			int yto = locatorTo.getLocation().y;
			ArrayList<String> values = dXAnddYValues(xto, yto, xFrom, yFrom, locatorTo.getSize().height,
					locatorFrom.getSize().height, difference);

			executeDragNDrop(firstElementlocator, values.get(0), values.get(1), stepWidth, stepDelay, index);
		} else {
			executeDragNDrop(firstElementlocator, dx, dy, stepWidth, stepDelay, index);
		}
	}

	@Override
	public void dragAndDrop(Locator firstElementlocator, By firstElementBy, Locator secondElementlocator,
			By secondElementBy, String stepWidth, String stepDelay, String dx, String dy) throws TestException, InterruptedException {
		WebElement locatorFrom = getElement(firstElementlocator, firstElementBy);
		int xFrom = locatorFrom.getLocation().x;
		int yFrom = locatorFrom.getLocation().y;
		int[] difference = { 0, 0 };
		addDragNDropScriptsToPage();

		if (dx == null && dy == null) {
			WebElement locatorTo = getElement(secondElementlocator, secondElementBy);
			int xto = locatorTo.getLocation().x;
			int yto = locatorTo.getLocation().y;
			ArrayList<String> values = dXAnddYValues(xto, yto, xFrom, yFrom, locatorTo.getSize().height,
					locatorFrom.getSize().height, difference);

			executeDragNDrop(firstElementlocator.value(), values.get(0), values.get(1), stepWidth, stepDelay);
		} else {
			executeDragNDrop(firstElementlocator.value(), dx, dy, stepWidth, stepDelay);
		}
	}

	@Override
	public void dragAndDrop(Locator dragable, By dragableby, Locator dropable, By dropableby, int timeTowait)
			throws TestException, InterruptedException {

		WebElement dragElement = null;
		WebElement dropElement = null;

		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(dragable,
				dragableby);
		List<WebElement> locate = SHelper.get().element().getListOf(dragable, dragableby);
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(timeTowait)).on(dragable,
				dragableby);
		dragElement = locate.get(0);
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(dragable,
				dragableby);
		List<WebElement> locateDroppable = SHelper.get().element().getListOf(dropable, dropableby);
		Actions act = new Actions(LocalDriver.getDriver());
		dropElement = locateDroppable.get(0);
		act.clickAndHold(dragElement).build().perform();
		Thread.sleep(5000);
		act.moveToElement(dropElement).build().perform();
		Thread.sleep(5000);
		act.release();
		act.release(dragElement).build().perform();
		Thread.sleep(5000);
	}

	@Override
	public void scrollTo(Locator locator, By by) throws TestException {
		WebElement element = getElement(locator, by);
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
	}

	@Override
	public void scrollTo(WebElement element) throws TestException {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
	}

	/**
	 * <summary> Method to execute the drag and drop
	 * javascript command </summary>
	 * 
	 * @param from
	 *            the selector string of the "from"
	 *            element
	 * @param dx
	 * @param dy
	 * @param stepWidth
	 * @param stepDelay
	 * @throws TestException
	 */
	private void executeDragNDrop(String from, String dx, String dy, String stepWidth, String stepDelay) {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("$('" + from + "').simulate('drag-n-drop', {dx: " + dx + ", dy: " + dy
						+ ", interpolation: {stepWidth: " + stepWidth + ", stepDelay: " + stepDelay + "}})");
	}

	/**
	 * <summary> Method to execute the drag and drop
	 * javascript command </summary>
	 * 
	 * @param from
	 *            the selector string of the "from"
	 *            element
	 * @param dx
	 * @param dy
	 * @param stepWidth
	 * @param stepDelay
	 * @param index
	 *            the index of the element to be
	 *            selected from a list of similar
	 *            elements
	 * @throws TestException
	 */
	private void executeDragNDrop(String from, String dx, String dy, String stepWidth, String stepDelay, String index) {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("$('" + from + "')[" + index + "].simulate('drag-n-drop', {dx: " + dx + ", dy: " + dy
						+ ", interpolation: {stepWidth: " + stepWidth + ", stepDelay: " + stepDelay + "}})");
	}

	/**
	 * <summary> Method to inject the drag and drop
	 * scripts into the page so that the drag and drop
	 * functionality can be used. </summary>
	 * 
	 * @return void
	 * @throws InterruptedException 
	 */
	private void addDragNDropScriptsToPage() throws InterruptedException {
		addScriptToPage("http://j-ulrich.github.io/jquery-simulate-ext/jquery.simulate.js");
		Thread.sleep(500);
		addScriptToPage("http://j-ulrich.github.io/jquery-simulate-ext/jquery.simulate.ext.js");
		Thread.sleep(500);
		addScriptToPage("http://j-ulrich.github.io/jquery-simulate-ext/jquery.simulate.drag-n-drop.js");
		Thread.sleep(500);
	}

	/**
	 * <summary> Method to inject a script to a
	 * webpage </summary>
	 * 
	 * @param script
	 *            the script that needs to be injected
	 * @return void
	 * @throws TestException
	 */
	private void addScriptToPage(String script) {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$.getScript('" + script + "')");
	}

	/**
	 * <summary> Method to get the dx and dy values
	 * and store them in an arrayList </summary>
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
	 * @throws TestException
	 */
	private ArrayList<String> dXAnddYValues(int x1, int y1, int x2, int y2, int y1Height, int y2Height,
			int[] difference) {
		ArrayList<String> values = new ArrayList<>();
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
	 * <summary> method for selecting from
	 * dropdown</summary>
	 * 
	 * @param element
	 *            - the DOM element from dropdown
	 * @param value
	 *            - the value to select from dropdown
	 * @param selectType
	 *            - the selection type eithe by value,
	 *            by visible text or by index
	 * @author OJ
	 */
	@Override
	public void selectFromDropDown(WebElement element, String value, SelectType selectType) throws TestException {
		Select sel = new Select(element);
		selectOption(sel, value, selectType);
	}

	/**
	 * <summary> method for selecting from
	 * dropdown</summary>
	 * 
	 * @param locator-
	 *            the DOM element from dropdown
	 * @param value
	 *            - the value to select from dropdown
	 * @param by
	 *            - the css selector
	 * @param selectType
	 *            - the selection type eithe by value,
	 *            by visible text or by index
	 * @author OJ
	 */
	@Override
	public void selectFromDropDown(Locator locator, By by, String value, SelectType selectType) throws TestException {
		Select sel = new Select(getElement(locator, by));
		selectOption(sel, value, selectType);
	}

	/**
	 * <summary>Make the selection</summary>
	 * 
	 * @param select
	 * @param value
	 * @param selectType
	 * @throws TestException
	 */
	private void selectOption(Select select, String value, SelectType selectType) throws TestException {
		switch (selectType) {
			case byValue:
				select.selectByValue(value);
				break;
			case byVisibleText:
				select.selectByVisibleText(value);
				break;
			case byIndex:
				select.selectByIndex(1);
				break;
			default:
				throw new TestException("A selection must be made");
		}
	}

	@Override
	public void dragAndDrop(WebElement elementToBeDragged, WebElement elementToBeDropped, int timeTowait)
			throws TestException, InterruptedException {
		WebElement dragElement = null;
		WebElement dropElement = null;

		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(timeTowait))
				.on(elementToBeDragged);
		dragElement = elementToBeDragged;
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20))
				.on(elementToBeDragged);
		Actions act = new Actions(LocalDriver.getDriver());
		dropElement = elementToBeDropped;
		act.clickAndHold(dragElement).build().perform();
		Thread.sleep(5000);
		act.moveToElement(dropElement).build().perform();
		Thread.sleep(5000);
		act.release();
		act.release(dragElement).build().perform();
		Thread.sleep(5000);
	}

}