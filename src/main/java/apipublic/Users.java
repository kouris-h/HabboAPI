package apipublic;

import fetch.Fetcher;
import hotel.Hotel;
import imager.HabboImager;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Users {

    /**
     * Find a user by username
     * @param hotel Hotel to search
     * @param username Username to find
     * @return User
     */
    public static <T extends User> T getUserByName(Hotel hotel, String username) {
        try {
            JSONObject userJson = Fetcher.fetchJSONObject(String.format("%sapi/public/users?name=%s", hotel.domain, username));
            if(userJson.has("error")) return null;
            return userJson.getBoolean("profileVisible") ? (T) new PublicUser(hotel, userJson) : (T) new PrivateUser(hotel, userJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    /**
     * Find a user by their Unique Id
     * @param hotel Hotel to search
     * @param userUniqueId User's unique Id
     * @return User
     */
    public static <T extends User> T getUserByUserUniqueId(Hotel hotel, String userUniqueId) {
        try {
            JSONObject userJson = Fetcher.fetchJSONObject(String.format("%sapi/public/users/%s", hotel.domain, userUniqueId));
            if(userJson.has("error")) return null;
            return userJson.getBoolean("profileVisible") ? (T) new PublicUser(hotel, userJson) : (T) new PrivateUser(hotel, userJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public abstract static class User {
        public final String uniqueId, name, figureString, motto;
        public final Date memberSince;
        public final boolean  profileVisible;
        public final List<Badges.SelectedBadge> selectedBadges;

        protected final Hotel hotel;

        public User(Hotel hotel, JSONObject jsonObject) {
            this.hotel = hotel;

            this.uniqueId = jsonObject.getString("uniqueId");
            this.name = jsonObject.getString("name");
            this.figureString = jsonObject.getString("figureString");
            this.motto = jsonObject.getString("motto");
            this.memberSince = Date.from(Instant.parse(jsonObject.getString("memberSince").substring(0, 22) + "Z"));

            this.profileVisible = jsonObject.getBoolean("profileVisible");
            this.selectedBadges = Badges.SelectedBadge.parseSelected(jsonObject.getJSONArray("selectedBadges"));
        }

        /**
         * Get an Instance of the AvatarBuilder with the user's current figureString
         * @return HabboImager.AvatarBuilder
         */
        public HabboImager.AvatarBuilder getFigureBuilder() {
            return HabboImager.getAvatarImageBuilder(hotel, name);
        }
    }

    public static class PublicUser extends User {
        public final Date lastAccessTime;
        public final int currentLevel, currentLevelCompletePercent, totalExperience, starGemCount;
        public final boolean online;

        public PublicUser(Hotel hotel, JSONObject jsonObject) {
            super(hotel, jsonObject);

            this.lastAccessTime = Date.from(Instant.parse(jsonObject.getString("lastAccesTime").substring(0, 22) + "Z"));

            this.currentLevel = jsonObject.getInt("currentLevel");
            this.currentLevelCompletePercent = jsonObject.getInt("currentLevelCompletePercent");
            this.totalExperience = jsonObject.getInt("totalExperience");
            this.starGemCount = jsonObject.getInt("starGemCount");

            this.online = jsonObject.getBoolean("online");
        }

        /**
         * Get the list of groups the user has joined
         * @return List of groups
         */
        public List<Groups.UserGroup> getGroups() {
            return Groups.getGroupsFromUserUniqueId(hotel, uniqueId);
        }

        /**
         * Get the list of the user's badges
         * @return List of badges
         */
        public List<Badges.Badge> getBadges() {
            return Badges.getBadgesFromUserUniqueId(hotel, uniqueId);
        }

        /**
         * Get the list of friends this user currently has
         * @return List of friends
         */
        public List<Friends.Friend> getFriends() {
            return Friends.getFriendsFromUserUniqueID(hotel, uniqueId);
        }

        /**
         * Get the list of rooms the user owns
         * @return List of rooms
         */
        public List<Rooms.Room> getRooms() {
            return Rooms.getRoomsFromUniqueUserId(hotel, uniqueId);
        }

        /**
         * Get the list of achievements the user completed
         * @return List of achievements
         */
        public List<Achievements.UserAchievement> getAchievements() {
            return Achievements.getAchievementsFromUser(hotel, uniqueId);
        }
    }

    private static class PrivateUser extends User {
        public PrivateUser(Hotel hotel, JSONObject jsonObject) {
            super(hotel, jsonObject);
        }
    }
}
