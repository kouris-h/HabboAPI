package gamedata.furnidata;

import fetch.Fetcher;
import gamedata.Gamedata;
import gamedata.furnidata.furnidetails.FloorItemDetails;
import gamedata.furnidata.furnidetails.FurniDetails;
import gamedata.furnidata.furnidetails.WallItemDetails;
import hotel.Hotel;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class FurniData extends Gamedata {
    private final HashMap<String, FurniDetails> floorFurniByClassName = new HashMap<>();
    private final HashMap<Integer, FurniDetails> floorFurniById = new HashMap<>();

    private final HashMap<String, FurniDetails> wallFurniByName = new HashMap<>();
    private final HashMap<Integer, FurniDetails> wallFurniById = new HashMap<>();

    public FurniData(Hotel selectedHotel) throws IOException {
        super(selectedHotel);
        System.out.println(System.currentTimeMillis());
        this.parseData(this.getJSONObject());
        System.out.println(System.currentTimeMillis());
    }

    @Override
    protected JSONObject getJSONObject() throws IOException {
        return Fetcher.fetchJSONObject(String.format("%sgamedata/furnidata_json/1", selectedHotel.domain));
    }

    @Override
    protected void parseData(JSONObject furnidataJson) {
        furnidataJson
                .getJSONObject("roomitemtypes") // Get Floor items
                .getJSONArray("furnitype")
                .toList()
                .parallelStream()
                .map(o -> (HashMap<String, Object>) o)
                .map(JSONObject::new)
                .map(FloorItemDetails::new)
                .forEach(details -> {
                    floorFurniByClassName.put(details.className, details);
                    floorFurniById.put(details.id, details);
                });

        furnidataJson
                .getJSONObject("wallitemtypes") // Get Wall items
                .getJSONArray("furnitype")
                .toList()
                .parallelStream()
                .map(o -> (HashMap<String, Object>) o)
                .map(JSONObject::new)
                .map(WallItemDetails::new)
                .forEach(details -> {
                    wallFurniByName.put(details.className, details);
                    wallFurniById.put(details.id, details);
                });
    }

    public FurniDetails getFurniDetailsByClassName(String className) {
        return floorFurniByClassName.getOrDefault(className, wallFurniByName.getOrDefault(className, null));
    }

    public FloorItemDetails getFloorItemDetailsByTypeID(int typeID) {
        return (FloorItemDetails) floorFurniById.getOrDefault(typeID, null);
    }

    public WallItemDetails getWallItemDetailsByTypeID(int typeID) {
        return (WallItemDetails) wallFurniById.getOrDefault(typeID, null);
    }
}
