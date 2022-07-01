package com.warnermedia.data.mongo.config;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Serializer {

    public static String serialize(MongoCursor<Document> collection) {
        String result = null;
        try {
            List<Document> list = new ArrayList<>();
            while (collection.hasNext()) {
                Document doc = collection.next();
                list.add(doc);
            }
            JSONObject obj = new JSONObject(list.get(0).toJson());
            result = obj.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public static String serialize(Document collection) {
        String result = null;
        try {
            JSONObject obj = new JSONObject(collection.toJson());
            result = obj.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
