package habbo.api.apipublic.achievements;

import java.util.Date;
import java.util.Objects;

public record AchievementData(int id, String name, Date creationTime, AchievementState state, String category) {}
