package habbo.api.apipublic.users;

import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.List;

public record UserFriend(String name, String motto, boolean online, String uniqueId,
                         String figureString) implements IUser {
    public User getAsUser() {
        return User.getByUniqueId(uniqueId);
    }

    public static List<UserFriend> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static List<UserFriend> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectList(String.format("%s/api/public/users/%s/friends", hotel, uniqueId), UserFriend.class);
    }
}
