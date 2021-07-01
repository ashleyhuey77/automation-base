package com.warnermedia.data.mongo.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.data.mongo.config.Serializer;
import com.warnermedia.data.mongo.models.Person;
import org.bson.Document;

public class PersonRepository {

    MongoDatabase database;

    public PersonRepository(MongoDatabase databaseMongo) {
        this.database = databaseMongo;
    }

    public void set() {
        Person data = null;
        try {
            MongoCollection<Document> collection = database.getCollection("person");
            String obj = Serializer.serialize(collection);
            final ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(obj, Person.class);
            DataMapper.setPerson(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
