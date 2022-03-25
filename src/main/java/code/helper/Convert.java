package code.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Convert {
    private static Logger log = LoggerFactory.getLogger(Convert.class);

    private static ObjectMapper objMapper;

    public static ObjectMapper getObjectMapper() {
        if (objMapper == null) {
            objMapper = new ObjectMapper();
            // objMapper.findAndRegisterModules(); // causes: Provider com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule not found
            objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        }
        return objMapper;
    }

    public static <T> T jsonToClass(Class<T> type, String jsonObj) {
        try {
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            return gson.fromJson(jsonObj, type);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return null;
    }

    public static String toJson(Object t) {
        String result = "";
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-DD'T'HH:mm:ss z")
                    .create();
            result = gson.toJson(t);
        } catch (Throwable ex) {
            // Report the error in the log and return an empty string
            log.error("Gson Exception  (Ignored) -  cause: {}", ex.getMessage());
        }
        return result;
    }

    public static String toJsonPretty(Object t) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(t);
        } catch (Throwable ex) {
            log.error("Gson Exception  (Ignored) -  cause: {}", ex.getMessage());
        }
        return "";
    }
}
