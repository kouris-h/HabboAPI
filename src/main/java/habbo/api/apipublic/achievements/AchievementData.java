package habbo.api.apipublic.achievements;

import java.time.LocalDate;

/**
 * Represents the data of the achievement as an element of {@link Achievement} or {@link UserAchievement}
 */
public record AchievementData(int id, String name, LocalDate creationTime, AchievementState state, String category) {}
