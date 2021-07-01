package com.warnermedia.config.data;

import com.warnermedia.config.State;
import com.warnermedia.config.TestException;
import com.warnermedia.config.app.ApplicationHelper;
import com.warnermedia.config.app.EnvironmentContext;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.data.loki.Environments;
import com.warnermedia.data.loki.Loki;
import com.warnermedia.data.loki.TestEnvironment;
import com.warnermedia.utils.Log;

import java.util.Random;
import java.util.logging.Level;

public class TestDataSetter extends ApplicationHelper {

	public static void setUpTestData() throws TestException {
		try {
			environment = LocalTest.getEnvironment().getEnvironment();
            EnvironmentContext context = new EnvironmentContext();
            State state;
            String env = environment.toUpperCase().trim();
            switch (env) {
                case "REF":
                    state = new RefState();
                    context.setState(state);
                    break;
                case "DEV":
                    state = new DevState();
                    context.setState(state);
					break;
                default:
                    throw new TestException("User did not provide an appropriate environment "
                    		+ "state. Cannot proceed with tests until a valid environment state is provided.");
            }
            context.doAction();
		} catch (Exception e) {
			Log.get().log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
}
