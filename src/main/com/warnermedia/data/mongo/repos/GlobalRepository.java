package com.warnermedia.data.mongo.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.data.mongo.config.Serializer;
import com.warnermedia.data.mongo.models.Global;
import com.warnermedia.data.mongo.models.Person;
import org.bson.Document;

public class GlobalRepository {

    MongoDatabase database;

    public GlobalRepository(MongoDatabase databaseMongo) {
        this.database = databaseMongo;
    }

    public void set() {
        Global data = null;
        try {
            MongoCollection<Document> collection = database.getCollection("global");
            String obj = Serializer.serialize(collection);
            final ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(obj, Global.class);
            DataMapper.setGlobal(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
