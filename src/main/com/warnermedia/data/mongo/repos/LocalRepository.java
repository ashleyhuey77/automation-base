package com.warnermedia.data.mongo.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.data.mongo.config.Serializer;
import com.warnermedia.data.mongo.models.Data;
import com.warnermedia.data.mongo.models.Global;
import com.warnermedia.data.mongo.models.Local;
import org.bson.Document;

public class LocalRepository {

    MongoDatabase database;

    public LocalRepository(MongoDatabase databaseMongo) {
        this.database = databaseMongo;
    }

    public void set() {
        Local data = null;
        try {
            MongoCollection<Document> collection = null;
            if (LocalTest.getEnvironment().getEnvironment().equalsIgnoreCase("ref")) {
               collection = database.getCollection("local.ref");
            } else if (LocalTest.getEnvironment().getEnvironment().equalsIgnoreCase("dev")) {
                collection = database.getCollection("local.dev");
            }
            String obj = Serializer.serialize(collection);
            final ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(obj, Local.class);
            DataMapper.setLocal(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
