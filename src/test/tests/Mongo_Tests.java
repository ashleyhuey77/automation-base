package tests;

import com.warnermedia.data.mongo.config.MongoConfig;
import com.warnermedia.data.mongo.config.DataMapper;
import org.testng.annotations.Test;

public class Mongo_Tests {

	@Test
	public void connectToMongodB() throws Exception {
        MongoConfig.createClient()
                    .initializeDB()
                    .setCollection()
                    .build();

        System.out.println(DataMapper.person().name());
	}

}
