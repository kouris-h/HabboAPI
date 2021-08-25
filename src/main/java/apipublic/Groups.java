package apipublic;

import fetch.Fetcher;
import hotel.Hotel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Groups {
    /**
     * Get a group from it's specific unique Id
     * @param hotel Hotel to search
     * @param groupID Unique group's Id
     * @return Group
     */
    public static Group getGroupFromUniqueID(Hotel hotel, String groupID) {
        try {
            JSONObject groupJson = Fetcher.fetchJSONObject(String.format("%sapi/public/groups/%s", hotel.domain, groupID));
            if(groupJson.has("error")) return null;

            return new Group(hotel, groupJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    /**
     * Get a list of groups from an user's Unique Id
     * @param hotel Hotel to search
     * @param userUniqueId User's unique Id
     * @return List<UserGroup>
     */
    public static List<UserGroup> getGroupsFromUserUniqueId(Hotel hotel, String userUniqueId) {
        try {
            JSONArray groupsJson = Fetcher.fetchJSONArray(String.format("%sapi/public/users/%s/groups", hotel.domain, userUniqueId));

            return UserGroup.parse(hotel, groupsJson);
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public static class Group {
        public final String id, name, description, type, roomId, badgeCode, primaryColour, secondaryColour;
        private final Hotel hotel;

        public Group(Hotel hotel, JSONObject jsonObject) {
            this.hotel = hotel;

            this.id = jsonObject.getString("id");
            this.name = jsonObject.getString("name");
            this.description = jsonObject.getString("description");
            this.type = jsonObject.getString("type");
            this.roomId = jsonObject.getString("roomId");
            this.badgeCode = jsonObject.getString("badgeCode");
            this.primaryColour = jsonObject.getString("primaryColour");
            this.secondaryColour = jsonObject.getString("secondaryColour");
        }

        /**
         * Get members of this group
         * @return List<Member>
         */
        public List<Member> getMembers() {
            try {
                JSONArray membersJson = Fetcher.fetchJSONArray(String.format("%sapi/public/groups/%s/members", hotel.domain, id));

                return Member.parse(hotel, membersJson);
            } catch (IOException | JSONException e) {
                return null;
            }
        }
    }

    public static class UserGroup extends Group {
        public final boolean online, isAdmin;

        public UserGroup(Hotel hotel, JSONObject jsonObject) {
            super(hotel, jsonObject);
            this.online = jsonObject.getBoolean("online");
            this.isAdmin = jsonObject.getBoolean("isAdmin");
        }

        public static List<UserGroup> parse(Hotel hotel, JSONArray jsonArray) {
            return Collections.unmodifiableList(jsonArray
                    .toList()
                    .parallelStream()
                    .map(o -> (HashMap<String, Object>) o)
                    .map(JSONObject::new)
                    .map(json -> new UserGroup(hotel, json))
                    .collect(Collectors.toList()));
        }
    }

    public static class Member {
        public final char gender;
        public final String motto, habboFigure, memberSince, uniqueId, name;
        public final boolean online, isAdmin;

        private final Hotel hotel;

        public Member(Hotel hotel, JSONObject jsonObject) {
            this.hotel = hotel;

            this.gender = jsonObject.getString("gender").charAt(0);
            this.motto = jsonObject.getString("motto");
            this.habboFigure = jsonObject.getString("habboFigure");
            this.memberSince = jsonObject.getString("memberSince");
            this.uniqueId = jsonObject.getString("uniqueId");
            this.name = jsonObject.getString("name");
            this.online = jsonObject.getBoolean("online");
            this.isAdmin = jsonObject.getBoolean("isAdmin");
        }

        public static List<Member> parse(Hotel hotel, JSONArray membersJson) {
            return Collections.unmodifiableList(
                    membersJson
                        .toList()
                        .parallelStream()
                        .map(o -> (HashMap<String, Object>) o)
                        .map(JSONObject::new)
                        .map(json -> new Member(hotel, json))
                        .collect(Collectors.toList())
            );
        }
        /**
         * Get a members full profile
         * @return [User] (PublicProfile/PrivateProfile) cast to specific class
         */
        public Users.User getFullProfile() {
            return Users.getUserByUserUniqueId(hotel, uniqueId);
        }
    }
}
