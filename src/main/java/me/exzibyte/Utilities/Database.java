package me.exzibyte.Utilities;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {

    static final Config config = new Config();
    static final String uri = config.get("dburi");
    private static final MongoClientURI clientURI = new MongoClientURI(uri);
    private static MongoClient client;
    private static MongoDatabase db;

    public static void connect() {
        client = new MongoClient(clientURI);
        db = client.getDatabase("Discord");
    }

    public static MongoCollection<Document> getCollection(String collection) {
        return db.getCollection(collection);
    }

    public static void close() {
        client.close();
    }


}
