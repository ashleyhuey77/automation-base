package common.utils.states;

import common.utils.TestUtils;
import common.utils.interfaces.State;

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