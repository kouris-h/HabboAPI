package gamedata;

import hotel.Hotel;
import org.json.JSONObject;
import java.io.IOException;

public abstract class Gamedata {
    protected Hotel selectedHotel;

    public Gamedata(Hotel selectedHotel) throws IOException {
        this.selectedHotel = selectedHotel;
    }

    protected abstract JSONObject getJSONObject() throws IOException;
    protected abstract void parseData(JSONObject jsonObject);
}
