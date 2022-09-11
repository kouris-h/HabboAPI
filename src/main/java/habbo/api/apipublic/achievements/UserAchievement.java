package habbo.api.apipublic.achievements;

import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.Set;

public record UserAchievement(AchievementData achievementData, int level, int score) {
    public static Set<UserAchievement> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static Set<UserAchievement> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/achievements/%s", hotel, uniqueId), UserAchievement.class);
    }

    public static void main(String[] args) {
        Set<UserAchievement> achievements = of("hhnl-9eb7b9e3742d3818eaa2b0b778d9d447");
        achievements.forEach(a -> System.out.println(a.achievementData.state()));
    }
}
