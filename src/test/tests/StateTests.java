package tests;

import org.apache.commons.lang3.NotImplementedException;
import org.testng.Assert;
import org.testng.annotations.Test;
import common.utils.contexts.EnvironmentContext;
import common.utils.contexts.HeadlessContext;
import common.utils.contexts.OSContext;
import common.utils.enums.Drivers;
import common.utils.enums.OS;
import common.utils.facades.HelperFacade;
import common.utils.interfaces.State;
import common.utils.states.HeadlessStop;
import common.utils.states.Linux;
import common.utils.states.Windows;
import log.Log;

public class StateTests {
	
	@Test
	public void verifyHeadlessStopState() throws Exception {
		Log.set();
		HeadlessContext context = new HeadlessContext();
		State headlessStopState = new HeadlessStop();
        context.setState(headlessStopState);
        context.doAction();
        State state = context.getState();
        Assert.assertNotNull(state);
	}
	
	@Test
	public void verifyLinuxState() throws Exception {
		OSContext context = new OSContext();
		State linux = new Linux();
        context.setState(linux);
        context.doAction();
        String value = System.getProperty("webdriver.chrome.driver");
        Assert.assertNotNull(value);
        State state = context.getState();
        Assert.assertNotNull(state);
	}
	
	@Test
	public void verifyWindowsState() throws Exception {
		OSContext context = new OSContext();
		State windows = new Windows();
        context.setState(windows);
        context.doAction();
        String value = System.getProperty("webdriver.chrome.driver");
        Assert.assertNotNull(value);
        State state = context.getState();
        Assert.assertNotNull(state);
	}
	
	@Test
	public void verifyHelperFacade() throws Exception {
		HelperFacade.setDriverLocalPathBasedOnOS(OS.LINUX);
		HelperFacade.setDriverLocalPathBasedOnOS(OS.WINDOWS);
	}
	
	@Test(expectedExceptions=NotImplementedException.class)
	public void verifyGetDriver_FF() throws Exception {
		HelperFacade.getDriver(Drivers.FIREFOX);
	}
	
	@Test(expectedExceptions=NotImplementedException.class)
	public void verifyGetDriver_Safari() throws Exception {
		HelperFacade.getDriver(Drivers.SAFARI);
	}
	
	@Test
	public void verifyEnvironmentCOntext() throws Exception {
		Log.set();
		EnvironmentContext context = new EnvironmentContext();
		State headlessStopState = new HeadlessStop();
        context.setState(headlessStopState);
        context.doAction();
        State state = context.getState();
        Assert.assertNotNull(state);
	}

}
