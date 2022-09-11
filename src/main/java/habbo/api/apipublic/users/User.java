package habbo.api.apipublic.users;

import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;

public record User(String uniqueId, String name, String figureString, String motto, LocalDateTime memberSince,
                   LocalDateTime lastAccessTime, boolean profileVisible, boolean online, int currentLevel,
                   int currentLevelCompletePercent, int totalExperience, int starGemCount,
                   List<SelectedBadge> selectedBadges) implements IUser {

    public User {
        selectedBadges = Collections.unmodifiableList(selectedBadges);
    }
    public static User getByName(Hotel hotel, String name) {
        return Fetcher.fetchObject(String.format("%s/api/public/users?name=%s", hotel, name), User.class);
    }

    public static User getByUniqueId(String uniqueId) {
        return getByUniqueId(Hotel.fromId(uniqueId), uniqueId);
    }

    public static User getByUniqueId(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObject(String.format("%s/api/public/users/%s", hotel, uniqueId), User.class);
    }

    public record SelectedBadge(int badgeIndex, String code, String name, String description) {
        public UserBadge getAsUserBadge() {
            return new UserBadge(code, name, description);
        }
    }
}
