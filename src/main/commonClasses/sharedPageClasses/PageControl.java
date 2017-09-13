package commonClasses.sharedPageClasses;

/**
 * <h1>PageControl</h1>
 * <p>An interface to enforce all page classes that inherit it
 * to use specific methods such as WaitForPageLoad.</p>
 * <p>An interface for the page and abstract base page classes was 
 * created due to the rapid growth of the automation.base and automation.tests
 * projects. It is meant to assure that in that growth, all methods inherit 
 * a better and more concise structure.</p>
 * @author ashleyhuey
 *
 */
public interface PageControl {
	
    /**
     * <p> WaitForPageLoad</p>
     * <p>The WaitForPageLoad method is a method defined in the Page control
     * interface. It is required for all pages to wait for the page to fully load
     * before attempting to execute the next test method in the chain as to reduce
     * uneccesary failures that are due to elements not yet being on the page.</p>
     * <p>The WaitForPageLoad method can be filled with any code as long as it
     * clearly defines a particular page being successfuly loaded so that methods do
     * not fail uneccesarily.</p>
     */
	public abstract void WaitForPageLoad() throws Exception;

}
