package gamedata;

import hotel.Habbo;
import org.json.JSONObject;
import java.io.IOException;

public abstract class Gamedata {
    protected Habbo selectedHabbo;

    public Gamedata(Habbo selectedHabbo) throws IOException {
        this.selectedHabbo = selectedHabbo;
    }

    protected abstract JSONObject getJSONObject() throws IOException;
    protected abstract void parseData(JSONObject jsonObject);
}
