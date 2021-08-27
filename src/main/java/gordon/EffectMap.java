package gordon;

import com.sun.org.apache.xpath.internal.operations.NotEquals;
import fetch.Fetcher;
import gamedata.external_variables.ExternalVariables;
import hotel.Hotel;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class EffectMap {
    private static Map<String, Effect> effectsByName = new HashMap<>();
    private static Map<String, Effect> effectsById = new HashMap<>();

    static {
        try {
            JSONObject effectMapJson = Fetcher.fetchXMLAsJSONObject(String.format("%seffectmap.xml", NewestProduction.client));
            List<Effect> effects = Effect.parse(effectMapJson);
            effectsByName = effects.stream().collect(Collectors.toMap(Effect::getName, Effect::getInstance));
            effectsById = effects.stream().collect(Collectors.toMap(Effect::getId, Effect::getInstance));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Effect getEffectByName(String name) {
        return effectsByName.getOrDefault(name, null);
    }

    public static Effect getEffectById(String id) {
        return effectsById.getOrDefault(id, null);
    }

    public static Effect getEffectById(int id) {
        return getEffectById(Integer.toString(id));
    }

    public static List<Effect> getAllEffects() {
        return Collections.unmodifiableList(new ArrayList<>(effectsByName.values()));
    }

    public static class Effect {
        public final String id, name, type;
        public final int revision;

        private Effect(JSONObject jsonObject) {
            this.id = "" + jsonObject.get("id");
            this.name = jsonObject.getString("lib");
            this.type = jsonObject.getString("type");
            this.revision = jsonObject.getInt("revision");
        }

        private String getName() {
            return name;
        }

        private String getId() {
            return id;
        }

        private Effect getInstance() {
            return this;
        }

        private static List<Effect> parse(JSONObject jsonObject) {
            return jsonObject
                .getJSONObject("map")
                .getJSONArray("effect")
                .toList()
                .parallelStream()
                .map(o -> (Map<String, Object>) o)
                .map(JSONObject::new)
                .map(Effect::new)
                .collect(Collectors.toList());
        }
    }
}
