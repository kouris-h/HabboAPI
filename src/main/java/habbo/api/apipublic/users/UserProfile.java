package habbo.api.apipublic.users;

import habbo.api.apipublic.rooms.Room;
import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.List;

public record UserProfile(User user, List<UserGroup> groups, List<UserBadge> badges, List<UserFriend> friends, List<Room> rooms) {
    public static UserProfile of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static UserProfile of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObject(String.format("%s/api/public/users/%s", hotel, uniqueId), UserProfile.class);
    }

    public static void main(String[] args) {
        UserProfile profile = Fetcher.fetchObject("https://www.habbo.com/api/public/users/hhnl-87d7cb3834568f08f910318b2b757882/profile", UserProfile.class);
        System.out.println(profile);
    }
}
