package habbo.api.apipublic.achievements;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserAchievementTest {
    static final Set<UserAchievement> KNOWN_ACHIEVEMENTS = Set.of(
            new UserAchievement(
                    new AchievementData(
                            3,
                            "EmailVerification",
                            LocalDate.parse("2008-09-30"),
                            AchievementState.ENABLED,
                            "identity"
                    ),
                    1,
                    0
            ),
            new UserAchievement(
                    new AchievementData(
                            17,
                            "RespectEarned",
                            LocalDate.parse("2008-10-28"),
                            AchievementState.ARCHIVED,
                            "social"
                    ),
                    10,
                    1166
            ),
            new UserAchievement(
                    new AchievementData(
                            249,
                            "AdvancedHorticulturist",
                            LocalDate.parse("2018-02-14"),
                            AchievementState.OFF_SEASON,
                            "games"
                    ),10,
                    80
            )
    );

    @Test
    void ofUniqueId() {
        assertTrue(UserAchievement.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee").containsAll(KNOWN_ACHIEVEMENTS));
    }
}