package com.warnermedia.data.mongo.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.data.mongo.config.Serializer;
import com.warnermedia.data.mongo.models.Asset;
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
