package habbo.api.apipublic.users;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserBadgeTest {
    final Set<UserBadge> KNOWN_BADGES = Set.of(
            new UserBadge(
                    "CNS08",
                    "Enge Badge van de Tijger",
                    "Habbo Chinees Nieuwjaar 2013"
            ),
            new UserBadge(
                    "NL666",
                    "Hoera! HabboMix viert de 15e verjaardag van Habbo BE/NL!",
                    ""
            ),
            new UserBadge(
                    "ACH_TetraBlokMasterPlayer3",
                    "Tetrablok Meester III",
                    "Omdat je 5 spelletjes Tetrablok gewonnen hebt!"
            ),
            new UserBadge(
                    "V2006",
                    "Zeldzame Tweekleurige Staarten",
                    ""
            ),
            new UserBadge(
                    "X2121",
                    "Zzzz..Zzzz..",
                    ""
            )
    );

    @Test
    void ofUniqueId() {
        Set<UserBadge> badges = UserBadge.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertEquals(1043, badges.size());
        assertTrue(badges.containsAll(KNOWN_BADGES));
    }
}