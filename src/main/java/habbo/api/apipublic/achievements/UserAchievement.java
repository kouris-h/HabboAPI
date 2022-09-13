package habbo.api.apipublic.achievements;

import habbo.api.apipublic.users.UserBadge;
import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.Set;

/**
 * Represents an achievement of a user obtained from {@code /api/achievements/<uniqueId>}
 */
public record UserAchievement(AchievementData achievement, int level, int score) {
    /**
     * Requests all achievements of a user by their unique id
     *
     * @habbo.api /api/public/achievements/&lt;{@code uniqueId}&gt;
     * @param uniqueId unique id of the user
     * @return Set of requested achievements (or {@code null} if user not found or private)
     * @implNote Extracts the hotel from the unique id and calls {@link UserAchievement#of(Hotel, String)}
     */
    public static Set<UserAchievement> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    /**
     * Requests all achievements of a user by their unique id and hotel
     *
     * @habbo.api /api/public/achievements/&lt;{@code uniqueId}&gt;
     * @param hotel hotel of the user
     * @param uniqueId unique id of the user
     * @return Set of requested achievements (or {@code null} if user not found or private)
     */
    public static Set<UserAchievement> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/achievements/%s", hotel, uniqueId), UserAchievement.class);
    }
}
