package habbo.api.apipublic.users;

import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.Set;

/**
 * Represents a badge obtained from {@code /api/users/<uniqueId>/badges}
 *
 * @author WiredSpast
 * @version 2.0
 * @since 1.0
 */
public record UserBadge(String code, String name, String description) {
    /**
     * Requests all badges of a user by their unique id
     *
     * @habbo.api /api/public/users/&lt;{@code uniqueId}&gt;/badges
     * @param uniqueId unique id of the user
     * @return Set of requested badges (or {@code null} if user not found or private)
     * @implNote Extracts the hotel from the unique id and calls {@link UserBadge#of(Hotel, String)}
     */
    public static Set<UserBadge> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    /**
     * Requests all badges of a user by their unique id and hotel
     *
     * @habbo.api /api/public/users/&lt;{@code uniqueId}&gt;/badges
     * @param hotel hotel of the user
     * @param uniqueId unique id of the user
     * @return Set of requested badges (or {@code null} if user not found or private)
     */
    public static Set<UserBadge> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/users/%s/badges", hotel, uniqueId), UserBadge.class);
    }
}
