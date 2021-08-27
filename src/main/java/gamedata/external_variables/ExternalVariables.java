package gamedata.external_variables;

import fetch.Fetcher;
import hotel.Hotel;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class ExternalVariables {

    private static Map<String, String> fetch() throws IOException {
       return Fetcher.fetchTxtAsMap(String.format("%sgamedata/external_variables/1", Hotel.SANDBOX.domain));
    }

    public static Map<String, String> getAllExternalVariables() throws IOException {
        return Collections.unmodifiableMap(fetch());
    }

    public static String getExternalVariable(String variable) throws IOException {
        return fetch().getOrDefault(variable, null);
    }

    public static String getProduction()  {
        try {
            return fetch().getOrDefault("flash.client.url", null);
        } catch (IOException e) {
            return null;
        }
    }

    public static class ExternalVariable {
        private final String key, value;

        public ExternalVariable(String[] a) {
            this.key = a[0];
            this.value = a.length>1? a[1]: "";
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

    }
}
