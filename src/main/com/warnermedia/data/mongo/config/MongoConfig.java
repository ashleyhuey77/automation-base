package com.warnermedia.data.mongo.config;

import com.app.SecurityHelper;
import com.app.file.*;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.utils.CredentialsType;
import com.warnermedia.config.TestException;
import com.warnermedia.config.data.UserHelper;

import com.warnermedia.config.driver.DriverFacade;
import com.warnermedia.config.driver.Drivers;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.data.mongo.repos.*;
import org.openqa.selenium.WebDriver;

public class MongoConfig {

    private static ConfigSteps THREAD_LOCAL;
    private static Object mutex = new Object();

    private MongoConfig() {

    }

    public static ClientStep getClient() {
        ConfigSteps localRef = THREAD_LOCAL;
        try {
            if (localRef == null) {
                synchronized (mutex) {
                    localRef = THREAD_LOCAL;
                    if (localRef == null) {
                        localRef = new ConfigSteps();
                        THREAD_LOCAL = localRef;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return localRef;
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
                ConnectionString connectionString = getEncryptedConnectionString();
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

        private ConnectionString getEncryptedConnectionString() throws Exception {
            ConnectionString result = null;
            try {
                FileEncrypterDecrypter fed = new FileEncrypterDecrypter();
                String fileName = CredentialsHelper.get(FileCredentialsType.DATABASE, Extension.ENC);
                FileCredentials credentials = FileCredentials.get(fed.decrypt(fileName, FileCredentialsType.DATABASE));
                return new ConnectionString("mongodb+srv://" + SecurityHelper.decrypt(credentials.name) + ":" + SecurityHelper.decrypt(credentials.password) + "@" + SecurityHelper.decrypt(credentials.uri));
            } catch (Exception e) {
                throw e;
            }
        }

        @Override
        public Boolean isConnectionOpen() {
            Boolean result = false;
            try {
                if ((mongoClient.get() != null)
                        && (database.get() != null)) {
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
