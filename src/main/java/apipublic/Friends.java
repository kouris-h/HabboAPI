package apipublic;

import fetch.Fetcher;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import hotel.Hotel;
import java.io.IOException;

public class Friends {
    /**
     * Retrieve a list of friends the chosen user has
     * NOTE: User MUST have a public profile to access the list of friends
     * @param hotel Hotel to search
     * @param userUniqueID User's unique Id
     * @return List<Friend>
     */
    public static List<Friend> getFriendsFromUserUniqueID(Hotel hotel, String userUniqueID) {
        try {
            JSONArray friendsJson = Fetcher.fetchJSONArray(String.format("%sapi/public/users/%s/friends", hotel.domain, userUniqueID));

            return Friend.parse(hotel, friendsJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public static class Friend {
        public final String name, motto, uniqueId, figureString;
        public final boolean online;

        private final Hotel hotel;

        private Friend(Hotel hotel, JSONObject jsonObject) {
            this.name = jsonObject.getString("name");
            this.motto = jsonObject.getString("motto");
            this.uniqueId = jsonObject.getString("uniqueId");
            this.figureString = jsonObject.getString("figureString");
            this.online = jsonObject.getBoolean("online");

            this.hotel = hotel;
        }

        public static List<Friend> parse(Hotel hotel, JSONArray jsonArray) {
            return Collections.unmodifiableList(
                    jsonArray
                        .toList()
                        .parallelStream()
                        .map(o -> (Map<String, Object>) o)
                        .map(JSONObject::new)
                        .map(json -> new Friend(hotel, json))
                        .collect(Collectors.toList())
            );
        }

        /**
         * Get a friends full profile
         * @return [User] (PublicProfile/PrivateProfile) cast to specific class
         */
        public Users.User getFullProfile() {
            return Users.getUserByUserUniqueId(hotel, uniqueId);
        }
    }
}
