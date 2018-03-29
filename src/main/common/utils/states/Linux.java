package common.utils.states;

import common.utils.TestUtils;
import common.utils.interfaces.State;

public class Linux implements State {

    @Override
    public void doAction() {
        try {
            System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/chromedriver");
        } catch (Exception e) {
            throw e;
        }
    }

}