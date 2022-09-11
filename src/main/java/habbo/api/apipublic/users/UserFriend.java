package habbo.api.apipublic.users;

import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.List;
import java.util.Set;

public record UserFriend(String name, String motto, boolean online, String uniqueId,
                         String figureString) implements IUser {
    public User getAsUser() {
        return User.getByUniqueId(uniqueId);
    }

    public static Set<UserFriend> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static Set<UserFriend> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/users/%s/friends", hotel, uniqueId), UserFriend.class);
    }
}
