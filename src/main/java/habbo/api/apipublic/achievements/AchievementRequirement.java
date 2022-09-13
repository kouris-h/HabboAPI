package habbo.api.apipublic.achievements;

/**
 * Represents a requirement necessary to achieve an {@link Achievement}
 */
public record AchievementRequirement(int level, int requiredScore) {}
