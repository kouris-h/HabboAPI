package fetch;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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

    public static BufferedImage fetchImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }
}
