package common.base;

import shelper.vobjects.By;
import shelper.vobjects.Locator;
import shelper.vobjects.TestElement;

public interface Type {
	
	public Locator locator();
	
	public By by();
	
	public TestElement element();

}
