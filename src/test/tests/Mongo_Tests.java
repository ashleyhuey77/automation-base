package tests;

import org.testng.annotations.Test;
import data.mongo.MongoContext;

public class Mongo_Tests {
	
	@Test
	public void connectToMongodB() throws Exception {
		String result = MongoContext.get().connectDb("tests_news_global").findByKey("api", "type", "MS");
		System.out.println(result);
	}

}
