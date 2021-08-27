import fetch.Fetcher;
import gordon.EffectMap;

import java.io.IOException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws IOException {
        // System.out.println(EffectMap.getEffectById(5).name);

        System.out.println(Fetcher.fetchTxtAsMap("https://www.habbo.nl/gamedata/external_variables/1").size());
    }
}
