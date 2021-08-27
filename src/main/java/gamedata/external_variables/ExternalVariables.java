package gamedata.external_variables;

import fetch.Fetcher;
import hotel.Hotel;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class ExternalVariables {

    private static Map<String, String> fetch(Hotel hotel) throws IOException {
       return Fetcher.fetchTxtAsMap(String.format("%sgamedata/external_variables/1", hotel.domain));
    }

    public static Map<String, String> getAllExternalVariables(Hotel hotel) throws IOException {
        return Collections.unmodifiableMap(fetch(hotel));
    }

    public static String getExternalVariable(Hotel hotel, String variable) throws IOException {
        return fetch(hotel).getOrDefault(variable, null);
    }

    public static String getProduction(Hotel hotel)  {
        try {
            return fetch(hotel).getOrDefault("flash.client.url", null);
        } catch (IOException e) {
            return null;
        }
    }
}
