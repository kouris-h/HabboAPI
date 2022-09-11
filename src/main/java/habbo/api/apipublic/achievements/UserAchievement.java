package habbo.api.apipublic.achievements;

import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.List;

public record UserAchievement(AchievementData achievementData, int level, int score) {
    public static List<UserAchievement> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static List<UserAchievement> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectList(String.format("%s/api/public/achievements/%s", hotel, uniqueId), UserAchievement.class);
    }

    public static void main(String[] args) {
        List<UserAchievement> achievements = of("hhnl-9eb7b9e3742d3818eaa2b0b778d9d447");
        achievements.forEach(a -> System.out.println(a.achievementData.state()));
    }
}