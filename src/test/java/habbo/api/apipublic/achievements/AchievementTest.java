package habbo.api.apipublic.achievements;

import habbo.api.hotel.Hotel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AchievementTest {
    static final Set<Achievement> KNOWN_ACHIEVEMENTS = Set.of(
            new Achievement(
                    new AchievementData(
                            3,
                            "EmailVerification",
                            LocalDate.parse("2008-09-30"),
                            AchievementState.ENABLED,
                            "identity"
                    ),
                    Set.of(
                            new AchievementRequirement(1, 1)
                    )
            ),
            new Achievement(
                    new AchievementData(
                            17,
                            "RespectEarned",
                            LocalDate.parse("2008-10-28"),
                            AchievementState.ARCHIVED,
                            "social"
                    ),
                    Set.of(
                            new AchievementRequirement(1, 1),
                            new AchievementRequirement(2, 6),
                            new AchievementRequirement(3, 16),
                            new AchievementRequirement(4, 66),
                            new AchievementRequirement(5, 166),
                            new AchievementRequirement(6, 366),
                            new AchievementRequirement(7, 566),
                            new AchievementRequirement(8, 766),
                            new AchievementRequirement(9, 966),
                            new AchievementRequirement(10, 1166)
                    )
            ),
            new Achievement(
                    new AchievementData(
                            249,
                            "AdvancedHorticulturist",
                            LocalDate.parse("2018-02-14"),
                            AchievementState.OFF_SEASON,
                            "games"
                    ),
                    Set.of(
                            new AchievementRequirement(1, 1),
                            new AchievementRequirement(2, 5),
                            new AchievementRequirement(3, 10),
                            new AchievementRequirement(4, 20),
                            new AchievementRequirement(5, 30),
                            new AchievementRequirement(6, 40),
                            new AchievementRequirement(7, 50),
                            new AchievementRequirement(8, 60),
                            new AchievementRequirement(9, 70),
                            new AchievementRequirement(10, 80)
                    )
            )
    );

    @Test
    void ofHotel() {
        assertTrue(Achievement.ofHotel(Hotel.NL).containsAll(KNOWN_ACHIEVEMENTS));
    }
}