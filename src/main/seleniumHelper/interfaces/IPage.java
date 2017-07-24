package seleniumHelper.interfaces;

import seleniumHelper.enums.Location;

public interface IPage {
	
	public void scrollTo(Location location) throws Exception;
	
	public void refresh() throws Exception;

}
