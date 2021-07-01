package com.warnermedia.config.data;

import com.warnermedia.config.State;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.utils.Log;

import java.util.logging.Level;

public class RefState implements State {

	@Override
	public void doAction() {
		DataMapper.setLocal(DataMapper.data().ref);
		Log.get().log(Level.INFO, "Tests have been set to run in the Ref environment. Running tests in Ref.");
	}

}
