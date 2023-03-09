package com.data.loki;

import com.data.config.DataMapper;
import com.data.loki.Asset;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.data.config.Serializer;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

@Slf4j
public class AssetRepository {

    MongoDatabase database;
    private static ThreadLocal<MongoCursor<Document>> cursor = new ThreadLocal<>();

    public AssetRepository(MongoDatabase databaseMongo) {
        this.database = databaseMongo;
    }

    public void set() {
        Asset data = null;
        try {
            MongoCollection<Document> collection = database.getCollection("asset");
            cursor.set(collection.find().iterator());
            String obj = Serializer.serialize(cursor.get());
            final ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(obj, Asset.class);
            DataMapper.setAsset(data);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void close() {
        try {
            cursor.get().close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
