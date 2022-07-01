package tests;

import com.utils.CredentialsType;
import com.warnermedia.config.settings.LocalTest;

import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.data.mongo.config.MongoConfig;
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
