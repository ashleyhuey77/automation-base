package common.utils.states;

import common.utils.TestUtils;
import common.utils.interfaces.State;

public class Mac implements State {

    @Override
    public void doAction() {
        try {
            System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        } catch (Exception e) {
            throw e;
        }
    }

}