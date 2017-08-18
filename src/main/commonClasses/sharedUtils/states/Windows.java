package commonClasses.sharedUtils.states;

import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.interfaces.State;

public class Windows implements State {

    @Override
    public void doAction() {
        try {
            System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/mchromedriver.exe");
        } catch (Exception e) {
            throw e;
        }
    }

}