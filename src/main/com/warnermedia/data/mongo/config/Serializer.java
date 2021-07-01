package com.warnermedia.data.mongo.config;

import com.mongodb.BasicDBList;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.json.JSONObject;

public class Serializer {

    public static String serialize(MongoCollection<Document> collection) {
        String result = null;
        try {
            MongoCursor<Document> iterator = collection.find().iterator();
            BasicDBList list = new BasicDBList();
            while (iterator.hasNext()) {
                Document doc = iterator.next();
                list.add(doc);
            }
            JSONObject obj = new JSONObject(JSON.serialize(list.get(0)));
            result = obj.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
