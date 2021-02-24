package me.exzibyte.Utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Config {
    public static JSONObject json;
    Logging logging = new Logging();
    /**
     * Load JSON configuration into memory
     *
     * @return JSONObject
     */
    public Config() {
        try {
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(new FileReader("quiver.json"));
        } catch (Exception e) {
            logging.error(this.getClass(), "Cannot load the configuration file.");
            System.exit(1);
        }
    }

    /**
     * Return the value of a key from configuration
     *
     * @param key
     * @return String value
     */
    public String get(String key) {
        return json.get(key).toString();
    }

}
