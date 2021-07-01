package com.warnermedia.config.data;

import com.warnermedia.config.State;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.utils.Log;

import java.util.logging.Level;

public class DevState implements State {

	@Override
	public void doAction() {
		DataMapper.setLocal(DataMapper.data().dev);
		Log.get().log(Level.INFO, "Tests have been set to run in the dev environment. Running tests in dev.");
	}

}
