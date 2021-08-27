package fetch;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Fetcher {
    public static JSONObject fetchJSONObject(String url) throws IOException {
        System.out.println(url);
        return new JSONObject(IOUtils.toString(new URL(url).openStream(), StandardCharsets.UTF_8));
    }

    public static JSONArray fetchJSONArray(String url) throws IOException {
        System.out.println(url);
        return new JSONArray(IOUtils.toString(new URL(url).openStream(), StandardCharsets.UTF_8));
    }

    public static JSONObject fetchXMLAsJSONObject(String url) throws IOException {
        System.out.println(url);
        return XML.toJSONObject(IOUtils.toString(new URL(url).openStream(), StandardCharsets.UTF_8));
    }

    public static Map<String, String> fetchTxtAsMap(String url) throws IOException {
        System.out.println(url);
        return new BufferedReader(new InputStreamReader(new URL(url).openStream()))
                .lines()
                .filter(l -> !l.isEmpty())
                .map(l -> new AbstractMap.SimpleEntry<>(l.substring(0, l.indexOf("=")), l.substring(l.indexOf("=") + 1)))
                .filter(distinctByKey(AbstractMap.SimpleEntry::getKey))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    private  static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static BufferedImage fetchImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }
}
