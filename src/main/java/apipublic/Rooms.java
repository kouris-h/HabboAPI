package apipublic;

import fetch.Fetcher;
import hotel.Hotel;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Rooms {

    public static Room getRoomById(Hotel hotel, int id) {
        try {
            JSONObject roomJson = Fetcher.fetchJSONObject(String.format("%sapi/public/rooms/%d", hotel.domain, id));
            if(roomJson.has("error")) return null;

            return new Room(roomJson);
        } catch (IOException e) {
            return null;
        }
    }

    public static class Room {
        public final int id, maximumVisitors, rating;
        public final String name, description, ownerName, ownerUniqueId, thumbnailUrl, imageUrl, doormode, uniqueId;
        public final Date creationTime;
        public boolean showOwnerName, isPublicRoom;

        public final List<String> tags, categories;

        private Room(JSONObject jsonObject) {
            this.id = jsonObject.getInt("id");
            this.maximumVisitors = jsonObject.getInt("maximumVisitors");
            this.rating = jsonObject.getInt("rating");

            this.name = jsonObject.getString("name");
            this.description = jsonObject.getString("description");
            this.ownerName = jsonObject.getString("ownerName");
            this.ownerUniqueId = jsonObject.getString("ownerUniqueId");
            this.thumbnailUrl = jsonObject.getString("thumbnailUrl");
            this.imageUrl = jsonObject.getString("imageUrl");
            this.doormode = jsonObject.getString("doorMode");
            this.uniqueId = jsonObject.getString("uniqueId");

            this.creationTime = Date.from(Instant.parse(jsonObject.getString("creationTime").substring(0, 22) + "Z"));

            this.showOwnerName = jsonObject.getBoolean("showOwnerName");
            this.isPublicRoom = jsonObject.getBoolean("publicRoom");

            this.tags = Collections.unmodifiableList(jsonObject.getJSONArray("tags").toList().stream().map(o -> (String) o).collect(Collectors.toList()));
            this.categories = Collections.unmodifiableList(jsonObject.getJSONArray("categories").toList().stream().map(o -> (String) o).collect(Collectors.toList()));
        }

        public BufferedImage getThumbnail() {
            return Fetcher.fetchImage(thumbnailUrl);
        }

        public BufferedImage getImage() {
            return Fetcher.fetchImage(imageUrl);
        }
    }
}
