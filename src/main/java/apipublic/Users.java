package apipublic;

import fetch.Fetcher;
import hotel.Hotel;
import imager.HabboImager;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Users {

    /**
     * Find a user by username
     * @param hotel Hotel to search
     * @param username Username to find
     * @return User
     */
    public static User getUserByName(Hotel hotel, String username) {
        try {
            JSONObject userJson = Fetcher.fetchJSONObject(String.format("%sapi/public/users?name=%s", hotel.domain, username));
            if(userJson.has("error")) return null;
            return userJson.getBoolean("profileVisible") ? new PublicUser(hotel, userJson) : new PrivateUser(hotel, userJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    /**
     * Find a user by his Unique Id
     * @param hotel Hotel to search
     * @param userUniqueId User's unique Id
     * @return User
     */
    public static User getUserByUserUniqueId(Hotel hotel, String userUniqueId) {
        try {
            JSONObject userJson = Fetcher.fetchJSONObject(String.format("%sapi/public/users/%s", hotel.domain, userUniqueId));
            if(userJson.has("error")) return null;
            return userJson.getBoolean("profileVisible") ? new PublicUser(hotel, userJson) : new PrivateUser(hotel, userJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public abstract static class User {
        public final String uniqueId, name, figureString, motto, memberSince;
        public final boolean  profileVisible;
        public final List<Badges.Badge> selectedBadges;

        protected final Hotel hotel;

        public User(Hotel hotel, JSONObject jsonObject) {
            this.hotel = hotel;

            this.uniqueId = jsonObject.getString("uniqueId");
            this.name = jsonObject.getString("name");
            this.figureString = jsonObject.getString("figureString");
            this.motto = jsonObject.getString("motto");
            this.memberSince = jsonObject.getString("memberSince");

            this.profileVisible = jsonObject.getBoolean("profileVisible");
            this.selectedBadges = Badges.SelectedBadge.parse(jsonObject.getJSONArray("selectedBadges"));
        }

        /**
         * Get an Instance of the AvatarBuilder with the user's current figureString
         * @return HabboImager.AvatarBuilder
         */
        public HabboImager.AvatarBuilder getFigureBuilder() {
            return HabboImager.getAvatarImageBuilder(hotel, name);
        }
    }

    private static class PublicUser extends User {
        public final String lastAccessTime;
        public final int currentLevel, currentLevelCompletePercent, totalExperience, starGemCount;
        public final boolean online;

        public PublicUser(Hotel hotel, JSONObject userJson) {
            super(hotel, userJson);

            this.lastAccessTime = userJson.getString("lastAccessTime");

            this.currentLevel = userJson.getInt("currentLevel");
            this.currentLevelCompletePercent = userJson.getInt("currentLevelCompletePercent");
            this.totalExperience = userJson.getInt("totalExperience");
            this.starGemCount = userJson.getInt("starGemCount");

            this.online = userJson.getBoolean("online");
        }

        /**
         * Get the list of groups the user has joined
         * @return List<Groups.UserGroup>
         */
        public List<Groups.UserGroup> getGroups() {
            return Groups.getGroupsFromUserUniqueId(hotel, uniqueId);
        }


        /**
         * Get the list of the user's badges
         * @return List<Badges.Badge>
         */
        public List<Badges.Badge> getBadges() {
            return Badges.getBadgesFromUserUniqueId(hotel, uniqueId);
        }

        /**
         * Get the list of friends this user currently has
         * @return List<Friends.Friend>
         */
        public List<Friends.Friend> getFriends() {
            return Friends.getFriendsFromUserUniqueID(hotel, uniqueId);
        }

        /**
         * Get the list of rooms the user owns
         * @return List<Rooms.Room>
         */
        public List<Rooms.Room> getRooms() {
            return Rooms.getRoomsByUniqueUserId(hotel, uniqueId);
        }
    }

    private static class PrivateUser extends User {
        public PrivateUser(Hotel hotel, JSONObject jsonObject) {
            super(hotel, jsonObject);
        }
    }
}
