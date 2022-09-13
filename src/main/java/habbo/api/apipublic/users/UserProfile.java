package habbo.api.apipublic.users;

import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.Set;

/**
 * Represents a profile obtained from {@code /api/users/<uniqueId>/profile}
 * (contains user, groups, badges, friends and rooms)
 *
 * @author WiredSpast
 * @version 2.0
 * @since 1.0
 */
public record UserProfile(User user, Set<UserGroup> groups, Set<UserBadge> badges, Set<UserFriend> friends, Set<UserRoom> rooms) {
    /**
     * Requests the full profile of a user by their unique id
     * (contains user,
     *
     * @habbo.api {@code /api/public/users/<uniqueId>/profile}
     * @param uniqueId unique id of the user
     * @return Profile of the requested user (or {@code null} if user not found or private)
     * @implNote Extracts the hotel from the unique id and calls {@link UserProfile#of(Hotel, String)}
     */
    public static UserProfile of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    /**
     * Requests the full profile of a user by their unique id and their hotel
     *
     * @habbo.api {@code /api/public/users/<uniqueId>/profile}
     * @param hotel hotel of the user
     * @param uniqueId unique id of the user
     * @return Profile of the requested user (contains user, groups, badges, friends and rooms) (or {@code null} if user not found or private)
     */
    public static UserProfile of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObject(String.format("%s/api/public/users/%s/profile", hotel, uniqueId), UserProfile.class);
    }
}
