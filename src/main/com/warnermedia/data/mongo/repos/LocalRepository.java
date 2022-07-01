package com.warnermedia.data.mongo.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.data.mongo.config.Serializer;
import com.warnermedia.data.mongo.models.Local;
import org.bson.Document;

public class LocalRepository {

    MongoDatabase database;
    private static ThreadLocal<MongoCursor<Document>> cursor = new ThreadLocal<>();

    public LocalRepository(MongoDatabase databaseMongo) {
        this.database = databaseMongo;
    }

    public void set() {
        Local data = null;
        try {
            Document doc = null;
            cursor.set(database.getCollection("local").find().iterator());
            if (LocalTest.getEnvironment().getEnvironment().equalsIgnoreCase("ref")) {
                while (cursor.get().hasNext()) {
                    doc = cursor.get().next();
                    if (doc.get("name").toString().equalsIgnoreCase("ref")) {
                        break;
                    }
                }
            } else if (LocalTest.getEnvironment().getEnvironment().equalsIgnoreCase("dev")) {
                while (cursor.get().hasNext()) {
                    doc = cursor.get().next();
                    if (doc.get("name").toString().equalsIgnoreCase("dev")) {
                        break;
                    }
                }
            } else {
                while (cursor.get().hasNext()) {
                    doc = cursor.get().next();
                    if (doc.get("name").toString().equalsIgnoreCase("ref")) {
                        break;
                    }
                }
            }
            String obj = Serializer.serialize(doc);
            final ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(obj, Local.class);
            DataMapper.setLocal(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void close() {
        try {
            cursor.get().close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
