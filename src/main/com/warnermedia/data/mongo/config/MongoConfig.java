package com.warnermedia.data.mongo.config;

import com.utils.CredentialsType;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.warnermedia.config.settings.SignInHelper;
import com.warnermedia.data.mongo.repos.*;
import com.warnermedia.wdm.utils.WebDriverManagerException;

public class MongoConfig {

    private MongoConfig() {

    }

    public static ClientStep getClient() {
            return new ConfigSteps();
    }

    public interface ClientStep {
        InitializeDBStep initializeDB() throws Exception;
        Boolean isConnectionOpen();
        void closeConnection();
    }

    public interface CloseConnectionStep {
        Boolean isConnectionOpen();
        void closeConnection();
    }

    public interface InitializeDBStep {
        SetCollectionStep setCollection();
    }

    public interface SetCollectionStep {
        void build();
    }

    private static class ConfigSteps implements ClientStep, InitializeDBStep, SetCollectionStep, CloseConnectionStep {

        private static ThreadLocal<MongoDatabase> database = new ThreadLocal<>();
        private static ThreadLocal<MongoClient> mongoClient = new ThreadLocal<>();

        @Override
        public InitializeDBStep initializeDB() throws Exception {
            try {
                ConnectionString connectionString = new ConnectionString("mongodb+srv://" + new String(SignInHelper.getName(CredentialsType.DEFAULT)) +
                        ":" + new String(SignInHelper.getPassword(CredentialsType.DEFAULT)) +
                        "@automation-qa-cluster.obla017.mongodb.net/?retryWrites=true&w=majority");
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .serverApi(ServerApi.builder()
                                .version(ServerApiVersion.V1)
                                .build())
                        .build();
                mongoClient.set(MongoClients.create(settings));
                database.set(mongoClient.get().getDatabase("loki"));
                return this;
            } catch (Exception e) {
                throw new Exception(e.getMessage() + " " + e.getStackTrace());
            }
        }

        @Override
        public Boolean isConnectionOpen() {
            Boolean result = false;
            try {
                if (mongoClient.get() != null
                && database.get() != null) {
                    result = true;
                }
            } catch (Exception e) {

            }
            return result;
        }

        @Override
        public SetCollectionStep setCollection() {
            new PersonRepository(database.get()).set();
            new VideoRepository(database.get()).set();
            new AssetRepository(database.get()).set();
            new GlobalRepository(database.get()).set();
            new LocalRepository(database.get()).set();
            return this;
        }

        @Override
        public void build() {

        }

        @Override
        public void closeConnection() {
            new PersonRepository(database.get()).close();
            new VideoRepository(database.get()).close();
            new AssetRepository(database.get()).close();
            new GlobalRepository(database.get()).close();
            new LocalRepository(database.get()).close();
            mongoClient.get().close();
        }
    }

}
