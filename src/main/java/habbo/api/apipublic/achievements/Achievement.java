package habbo.api.apipublic.achievements;

import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.List;
import java.util.Set;

public record Achievement(AchievementData achievement, Set<AchievementRequirement> levelRequirements) {
    public static Set<Achievement> ofHotel(Hotel hotel) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/achievements", hotel), Achievement.class);
    }

    public static void main(String[] args) {
        System.out.println(ofHotel(Hotel.NL));
    }
}
