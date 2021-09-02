package gamedata.furnidata;

import fetch.Fetcher;
import gamedata.Gamedata;
import gamedata.furnidata.furnidetails.FloorItemDetails;
import gamedata.furnidata.furnidetails.FurniDetails;
import gamedata.furnidata.furnidetails.WallItemDetails;
import hotel.Hotel;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FurniData extends Gamedata {
    private final HashMap<String, FurniDetails> floorFurniByClassName = new HashMap<>();
    private final HashMap<Integer, FurniDetails> floorFurniById = new HashMap<>();

    private final HashMap<String, FurniDetails> wallFurniByName = new HashMap<>();
    private final HashMap<Integer, FurniDetails> wallFurniById = new HashMap<>();

    public FurniData(Hotel selectedHotel) throws IOException {
        super(selectedHotel);
        this.parseData(this.getJSONObject());
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

    /**
     * Search the furnidata to find a furni which matches the given classname
     * @param className Classname (unique) of the sought furni
     * @return FurniDetails
     */
    public <ItemDetails extends FurniDetails> ItemDetails getFurniDetailsByClassName(String className) {
        return (ItemDetails) floorFurniByClassName.getOrDefault(className, wallFurniByName.getOrDefault(className, null));
    }

    /**
     * Search the furnidata to find a FloorFurni which matches the given typeID
     * @param typeID TypeID of the sought furni
     * @return FurniDetails
     */
    public FloorItemDetails getFloorItemDetailsByTypeID(int typeID) {
        return (FloorItemDetails) floorFurniById.getOrDefault(typeID, null);
    }

    /**
     * Search the furnidata to find a WallFurni which matches the given typeID
     * @param typeID TypeID of the sought furni
     * @return FurniDetails
     */
    public WallItemDetails getWallItemDetailsByTypeID(int typeID) {
        return (WallItemDetails) wallFurniById.getOrDefault(typeID, null);
    }

    public List<FloorItemDetails> getAllFloorItems() {
        return Collections.unmodifiableList(floorFurniById.values().parallelStream().map(i -> (FloorItemDetails) i).collect(Collectors.toList()));
    }

    public List<WallItemDetails> getAllWallItems() {
        return Collections.unmodifiableList(wallFurniById.values().parallelStream().map(i -> (WallItemDetails) i).collect(Collectors.toList()));
    }
}
