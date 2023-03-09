package com.data.loki;

import com.config.setup.app.LocalTest;
import com.data.config.DataMapper;
import com.data.loki.Local;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.data.config.Serializer;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

@Slf4j
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
