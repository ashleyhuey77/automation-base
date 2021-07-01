package com.warnermedia.data.mongo.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.data.mongo.config.Serializer;
import com.warnermedia.data.mongo.models.Asset;
import org.bson.Document;

public class AssetRepository {

    MongoDatabase database;

    public AssetRepository(MongoDatabase databaseMongo) {
        this.database = databaseMongo;
    }

    public void set() {
        Asset data = null;
        try {
            MongoCollection<Document> collection = database.getCollection("asset");
            String obj = Serializer.serialize(collection);
            final ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(obj, Asset.class);
            DataMapper.setAsset(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
