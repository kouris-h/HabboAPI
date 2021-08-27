package fetch;

import gamedata.external_variables.ExternalVariables;
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
import java.util.Map;
import java.util.stream.Collectors;

import static utils.Utils.distinctByKey;

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
               .filter(s -> !s.isEmpty())
               .map(s -> s.split("="))
               .map(ExternalVariables.ExternalVariable::new)
               .filter(distinctByKey(ExternalVariables.ExternalVariable::getKey))
               .collect(Collectors.toMap(ExternalVariables.ExternalVariable::getKey, ExternalVariables.ExternalVariable::getValue));
    }

    public static BufferedImage fetchImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }
}
