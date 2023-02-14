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
import com.warnermedia.data.mongo.models.Global;
import com.warnermedia.data.mongo.models.Person;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

@Slf4j
public class GlobalRepository {

    MongoDatabase database;
    private static ThreadLocal<MongoCursor<Document>> cursor = new ThreadLocal<>();

    public GlobalRepository(MongoDatabase databaseMongo) {
        this.database = databaseMongo;
    }

    public void set() {
        Global data = null;
        try {
            MongoCollection<Document> collection = database.getCollection("global");
            cursor.set(collection.find().iterator());
            String obj = Serializer.serialize(cursor.get());
            final ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(obj, Global.class);
            DataMapper.setGlobal(data);
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
