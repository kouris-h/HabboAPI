package habbo.api.apipublic.users;

import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.Set;

/**
 * Represents a friend obtained from {@code /api/users/<uniqueId>/friends}
 *
 * @author WiredSpast
 * @version 2.0
 * @since 1.0
 */
public record UserFriend(String name, String motto, boolean online, String uniqueId,
                         String figureString) implements IUser {

    /**
     * Requests the full user information of the friend
     *
     * @habbo.api {@code /api/public/users/<uniqueId>}
     * @return Requested
     */
    public User getAsUser() {
        return User.getByUniqueId(uniqueId);
    }

    /**
     * Requests all friends of a user by their unique id
     *
     * @habbo.api {@code /api/public/users/<uniqueId>/friends}
     * @param uniqueId unique id of the user
     * @return Set of requested friends (or {@code null} if user not found or private)
     * @implNote Extracts the hotel from the unique id and calls {@link UserFriend#of(Hotel, String)}
     */
    public static Set<UserFriend> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    /**
     * Requests all friends of a user by their unique id and their hotel
     *
     * @habbo.api {@code /api/public/users/<uniqueId>/friends}
     * @param hotel hotel of the user
     * @param uniqueId unique id of the user
     * @return Set of requested friends (or {@code null} if user not found or private)
     */
    public static Set<UserFriend> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/users/%s/friends", hotel, uniqueId), UserFriend.class);
    }
}
