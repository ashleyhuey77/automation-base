package com.warnermedia.data.mongo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.warnermedia.data.mongo.repos.*;
import org.bson.Document;

import java.io.IOException;

public class MongoConfig {

    private MongoConfig() {

    }

    public static ClientStep createClient() {
            return new ConfigSteps();
    }

    public interface ClientStep {
        InitializeDBStep initializeDB();
    }

    public interface InitializeDBStep {
        SetCollectionStep setCollection();
    }

    public interface SetCollectionStep {
        void build();
    }

    private static class ConfigSteps implements ClientStep, InitializeDBStep, SetCollectionStep {

        private MongoDatabase database;

        @Override
        public InitializeDBStep initializeDB() {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://ahuey:MTINlAz0gJdRKrgx@cluster0.pv1yh.mongodb.net/loki?retryWrites=true&w=majority");

            MongoClient mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase("loki");
            return this;
        }

        @Override
        public SetCollectionStep setCollection() {
            new PersonRepository(database).set();
            new VideoRepository(database).set();
            new AssetRepository(database).set();
            new GlobalRepository(database).set();
            new LocalRepository(database).set();
            return this;
        }

        @Override
        public void build() {

        }
    }

}
