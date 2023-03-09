package tests;

import com.config.setup.app.LocalTest;

import com.data.config.DataMapper;
import com.data.config.MongoConfig;
import org.testng.annotations.Test;

public class Mongo_Tests {

	@Test
	public void connectToMongodB() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


        System.out.println(DataMapper.person().name());

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB2() throws Exception {
		LocalTest.initializeSettings();
		System.out.println(DataMapper.person().name());

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}
}
