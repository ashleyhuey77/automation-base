package tests;

import com.utils.CredentialsType;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.config.settings.SignInHelper;
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


        System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB2() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB3() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB4() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB5() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB6() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB7() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB8() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB9() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB10() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB11() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB12() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB13() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB14() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB15() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB16() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB17() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB18() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB19() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

	@Test
	public void connectToMongodB20() throws Exception {
		LocalTest.initializeSettings();
		if (!MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient()
					.initializeDB()
					.setCollection()
					.build();
		}


		System.out.println(DataMapper.person().phoneNumber());
		System.out.println(DataMapper.local().midasLocation);

		if (MongoConfig.getClient().isConnectionOpen()) {
			MongoConfig.getClient().closeConnection();
		}
	}

}
