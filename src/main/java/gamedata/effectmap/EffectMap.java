package gamedata.effectmap;

import fetch.Fetcher;
import gamedata.Gamedata;
import gamedata.external_variables.ExternalVariables;
import hotel.Hotel;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.Utils.distinctByKey;

public class EffectMap extends Gamedata {

    private Map<String, Effect> effects;

    public EffectMap() throws IOException {
        super(Hotel.SANDBOX);
        this.parseData(getJSONObject());
    }

    @Override
    protected JSONObject getJSONObject() throws IOException {
        return Fetcher.fetchXMLAsJSONObject(String.format("%seffectmap.xml", ExternalVariables.getProduction()));
    }

    @Override
    protected void parseData(JSONObject effectJson) {
       this.effects =
           effectJson
               .getJSONObject("map")
               .getJSONArray("effect")
               .toList()
               .parallelStream()
               .map(o -> (HashMap<String, Object>) o)
               .map(JSONObject::new)
               .map(Effect::new)
               .filter(distinctByKey(Effect::getName))
               .collect(Collectors.toMap(Effect::getName, Effect::getInstance));
    }

    public Map<String, Effect> getAllEffects() {
        return Collections.unmodifiableMap(effects);
    }

    public Effect getEffect(String name) {
        return effects.getOrDefault(name, null);
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

        public String getName() {
            return name;
        }

        public Effect getInstance() {
            return this;
        }
    }
}
