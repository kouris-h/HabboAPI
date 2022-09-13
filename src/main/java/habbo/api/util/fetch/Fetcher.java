package habbo.api.util.fetch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Static helper class to fetch jsonData and deserialize it to objects
 *
 * @author WiredSpast
 * @version 2.0
 * @since 1.0
 */
public final class Fetcher {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        module.addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE);
        module.addDeserializer(Color.class, ColorDeserializer.INSTANCE);
        mapper.registerModule(module);
    }

    /**
     * Fetches and deserializes jsonObject from URL and deserialize it to an instance of the provided class
     *
     * @param url URL (in String form) to fetch jsonObject from
     * @param cls Class of the object the data has to be deserialized to
     * @return Deserialized object (or {@code null} if request is invalid)
     */
    public static <T> T fetchObject(String url, Class<T> cls) {
        try {
            return mapper.readValue(new URL(url), cls);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Fetches and deserializes json array from the given URL and deserialize it to a {@link List} of instances of the provided class
     *
     * @param url URL to fetch json array from (in String form)
     * @param cls Class of the object the elements of the array have to be deserialized to
     * @return {@link List} of deserialized objects (or {@code null} if request is invalid)
     */
    public static <T> List<T> fetchObjectList(String url, Class<T> cls) {
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, cls);
            return mapper.readValue(new URL(url), listType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Fetches and deserializes json array from the given URL and deserialize it to a {@link Set} of instances of the provided class
     *
     * @param url URL to fetch jsonArray from (in String form)
     * @param cls Class of the object the elements of the array have to be deserialized to
     * @return {@link Set} of deserialized objects (or {@code null} if request is invalid)
     */
    public static <T> Set<T> fetchObjectSet(String url, Class<T> cls) {
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(HashSet.class, cls);
            return mapper.readValue(new URL(url), listType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Fetches and deserializes json array from the given URL and deserialize it to an array of the provided array class
     *
     * @param url URL to fetch jsonArray from (in String form)
     * @param arrayCls Array class of the object the elements of the array have to be deserialized to
     * @return Array of deserialized objects (or {@code null} if request is invalid)
     */
    public static <T> T[] fetchObjectArray(String url, Class<T[]> arrayCls) {
        try {
            return mapper.readValue(new URL(url), arrayCls);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
