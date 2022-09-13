package habbo.api.apipublic.users;

import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents a user obtained from {@code /api/users?name=<username>} or {@code /api/users/<uniqueId>}
 *
 * @author WiredSpast
 * @version 2.0
 * @since 1.0
 */
public record User(String uniqueId, String name, String figureString, String motto, LocalDateTime memberSince,
                   LocalDateTime lastAccessTime, boolean profileVisible, boolean online, int currentLevel,
                   int currentLevelCompletePercent, int totalExperience, int starGemCount,
                   Set<SelectedBadge> selectedBadges) implements IUser {
    /**
     * Requests a user by their username
     *
     * @habbo.api /api/public/users?name=&lt;name&gt;
     * @param hotel hotel of the user you want to request
     * @param name username of the User you want to request
     * @return requested user (or {@code null} if user not found)
     */
    public static User getByName(Hotel hotel, String name) {
        return Fetcher.fetchObject(String.format("%s/api/public/users?name=%s", hotel, name), User.class);
    }

    /**
     * Requests a user by their unique id
     *
     * @habbo.api /api/public/users/&lt;uniqueId&gt;
     * @param uniqueId unique id of the User you want to request
     * @return requested user (or {@code null} if user not found)
     * @implNote Extracts the hotel from the unique id and calls {@link User#getByUniqueId(Hotel, String)}
     */
    public static User getByUniqueId(String uniqueId) {
        return getByUniqueId(Hotel.fromId(uniqueId), uniqueId);
    }

    /**
     * Requests a user by their unique id
     *
     * @habbo.api /api/public/users/&lt;uniqueId&gt;
     * @param hotel hotel of the user you want to request
     * @param uniqueId unique id of the User you want to request
     * @return requested user (or {@code null} if user not found)
     */
    public static User getByUniqueId(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObject(String.format("%s/api/public/users/%s", hotel, uniqueId), User.class);
    }

    /**
     * Represents a selected badge as part of the User object
     *
     * @author WiredSpast
     * @version 2.0
     * @since 1.0
     */
    public record SelectedBadge(int badgeIndex, String code, String name, String description) {
        /**
         * Get the badge as a {@link UserBadge} instance
         * @return {@link UserBadge} instance of the badge
         */
        public UserBadge getAsUserBadge() {
            return new UserBadge(code, name, description);
        }
    }
}
