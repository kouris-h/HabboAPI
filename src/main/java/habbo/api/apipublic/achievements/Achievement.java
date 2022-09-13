package habbo.api.apipublic.achievements;

import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.Set;

/**
 * Represents an achievement obtained from {@code /api/achievements}
 */
public record Achievement(AchievementData achievement, Set<AchievementRequirement> levelRequirements) {
    public static Set<Achievement> ofHotel(Hotel hotel) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/achievements", hotel), Achievement.class);
    }
}
