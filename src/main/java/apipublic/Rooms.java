package apipublic;

import fetch.Fetcher;
import hotel.Hotel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

public class Rooms {

    /**
     * Get information of a room based on the room id
     * @param hotel hotel to search
     * @param id room id
     * @return Room
     */
    public static Room getRoomById(Hotel hotel, int id) {
        try {
            JSONObject roomJson = Fetcher.fetchJSONObject(String.format("%sapi/public/rooms/%d", hotel.domain, id));
            if(roomJson.has("error")) return null;

            return new Room(hotel, roomJson);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Get rooms of a user
     * @param hotel hotel to search
     * @param uniqueUserId unique userId
     * @return List<Room>
     */
    public static List<Room> getRoomsFromUniqueUserId(Hotel hotel, String uniqueUserId) {
        try {
            JSONArray roomsJson = Fetcher.fetchJSONArray(String.format("%sapi/public/users/%s/rooms", hotel.domain, uniqueUserId));

            return Room.parse(hotel, roomsJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public static class Room {
        public final int id, maximumVisitors, rating;
        public final String name, description, ownerName, ownerUniqueId, habboGroupId, thumbnailUrl, imageUrl, doormode, uniqueId;
        public final Date creationTime;
        public boolean showOwnerName, isPublicRoom;

        public final List<String> tags, categories;

        private final Hotel hotel;

        private Room(Hotel hotel, JSONObject jsonObject) {
            this.hotel = hotel;

            this.id = jsonObject.getInt("id");
            this.maximumVisitors = jsonObject.getInt("maximumVisitors");
            this.rating = jsonObject.getInt("rating");

            this.name = jsonObject.getString("name");
            this.description = jsonObject.getString("description");
            this.ownerName = jsonObject.getString("ownerName");
            this.ownerUniqueId = jsonObject.getString("ownerUniqueId");
            this.habboGroupId = jsonObject.optString("habboGroupId", null);
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

        private static List<Room> parse(Hotel hotel, JSONArray jsonArray) {
            return Collections.unmodifiableList(
                    jsonArray
                        .toList()
                        .parallelStream()
                        .map(o -> (Map<String, Object>) o)
                        .map(JSONObject::new)
                        .map(json -> new Room(hotel, json))
                        .collect(Collectors.toList())
            );
        }

        /**
         * Get thumbnail of room if present
         * @return Thumbnail as BufferedImage
         */
        public BufferedImage getThumbnail() {
            return Fetcher.fetchImage(thumbnailUrl);
        }

        /**
         * Get screenshot of room if present
         * @return Screenshot as BufferedImage
         */
        public BufferedImage getImage() {
            return Fetcher.fetchImage(imageUrl);
        }

        /**
         * Get group of room if present
         * @return Group object
         */
        public Groups.Group getGroup() {
            return this.habboGroupId != null ? Groups.getGroupFromUniqueID(this.hotel, this.habboGroupId) : null;
        }

        /**
         * Get profile of room owner
         * @return User object
         */
        public Users.User getOwnerProfile() {
            return Users.getUserByUserUniqueId(hotel, ownerUniqueId);
        }
    }
}
