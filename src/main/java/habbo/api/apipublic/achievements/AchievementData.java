package habbo.api.apipublic.achievements;

import java.util.Date;

public record AchievementData(int id, String name, Date creationTime, AchievementState state, String category) {}
