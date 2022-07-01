package com.warnermedia.data.mongo.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.data.mongo.config.Serializer;
import com.warnermedia.data.mongo.models.Video;
import org.bson.Document;

public class VideoRepository {
    MongoDatabase database;
    private static ThreadLocal<MongoCursor<Document>> cursor = new ThreadLocal<>();

    public VideoRepository(MongoDatabase databaseMongo) {
        this.database = databaseMongo;
    }

    public void set() {
        Video data = null;
        try {
            MongoCollection<Document> collection = database.getCollection("video");
            cursor.set(collection.find().iterator());
            String obj = Serializer.serialize(cursor.get());
            final ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(obj, Video.class);
            DataMapper.setVideo(data);
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
