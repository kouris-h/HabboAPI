package habbo.api.apipublic.achievements;

import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.List;

public record Achievement(AchievementData achievement, List<AchievementRequirement> levelRequirements) {
    public static List<Achievement> ofHotel(Hotel hotel) {
        return Fetcher.fetchObjectList(String.format("%s/api/public/achievements", hotel), Achievement.class);
    }

    public static void main(String[] args) {
        System.out.println(ofHotel(Hotel.NL));
    }
}
