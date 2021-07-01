package com.warnermedia.data.mongo.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.data.mongo.config.Serializer;
import com.warnermedia.data.mongo.models.Video;
import org.bson.Document;

public class VideoRepository {
    MongoDatabase database;

    public VideoRepository(MongoDatabase databaseMongo) {
        this.database = databaseMongo;
    }

    public void set() {
        Video data = null;
        try {
            MongoCollection<Document> collection = database.getCollection("video");
            String obj = Serializer.serialize(collection);
            final ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(obj, Video.class);
            DataMapper.setVideo(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
