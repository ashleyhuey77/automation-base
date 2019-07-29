package data.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoContext {

	private static MongoContext ctx = new MongoContext();
	private MongoClient client;
	private MongoDatabase db;
	private List<String> jsonString;

	private MongoContext() {
		try {
			init();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void init() throws UnknownHostException {
		MongoCredential credential = MongoCredential.createCredential("bpsAdmin", "admin", "nit6#deg".toCharArray());

	    MongoClientSettings settings = MongoClientSettings.builder()
	            .credential(credential)
	            .applyToSslSettings(builder -> builder.enabled(false))
	            .applyToClusterSettings(builder -> 
	                builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
	            .build();
	    this.client = MongoClients.create(settings);
	}

	public static MongoContext get() {
		return ctx;
	}
	
	Block<Document> getBlock = new Block<Document>() {
		@Override
		public void apply(Document t) {
			jsonString.add(t.toJson());
		}
	};

	public MongoContext connectDb(String dbname) {
		if (db != null) {
			throw new RuntimeException("Already conected to " + db.getName() + "can't connect " + dbname);
		}
		this.db = client.getDatabase(dbname);
		System.out.println("DB Details :: " + db.getName());
		return ctx;
	}

	public <T, X> String findByKey(String collectionName, String key, T value) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		jsonString = new ArrayList<String>();
		collection.find(eq(key, value)).forEach(getBlock);
		String cursor = turnListToString();
		return cursor;
	}
	
	private String turnListToString() {
		String result = null;
		for (String json : jsonString) {
			result = result + " " + json;
		}
		return result;
	}

}
