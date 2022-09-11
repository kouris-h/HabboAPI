package habbo.api.fetch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import habbo.api.util.deserialize.ColorDeserializer;
import habbo.api.util.deserialize.LocalDateTimeDeserializer;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fetcher {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        module.addDeserializer(Color.class, ColorDeserializer.INSTANCE);
        mapper.registerModule(module);
    }

    public static <T> T fetchObject(String url, Class<T> cls) {
        try {
            return mapper.readValue(new URL(url), cls);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static <T> List<T> fetchObjectList(String url, Class<T> cls) {
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, cls);
            return mapper.readValue(new URL(url), listType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static <T> Set<T> fetchObjectSet(String url, Class<T> cls) {
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(HashSet.class, cls);
            return mapper.readValue(new URL(url), listType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static <T> T[] fetchObjectArray(String url, Class<T[]> arrayCls) {
        try {
            return mapper.readValue(new URL(url), arrayCls);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
