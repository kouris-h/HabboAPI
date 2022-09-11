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
import java.util.List;

public class Fetcher {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleModule timeModule = new SimpleModule();
        timeModule.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        mapper.registerModule(timeModule);
        SimpleModule colorModule = new SimpleModule();
        colorModule.addDeserializer(Color.class, ColorDeserializer.INSTANCE);
        mapper.registerModule(colorModule);
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
}
