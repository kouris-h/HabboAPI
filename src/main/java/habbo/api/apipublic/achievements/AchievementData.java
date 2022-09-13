package habbo.api.apipublic.achievements;

import java.util.Date;

/**
 * Represents the data of the achievement as an element of {@link Achievement} or {@link UserAchievement}
 */
public record AchievementData(int id, String name, Date creationTime, AchievementState state, String category) {}
