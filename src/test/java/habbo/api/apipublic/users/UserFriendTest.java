package habbo.api.apipublic.users;

import habbo.api.hotel.Hotel;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserFriendTest {
    final UserFriend KOURIS_FRIEND = new UserFriend(
            "Kouris",
            "soon: Wraithy Manor 2",
            false,
            "hhnl-d8b18f97c708a10b659cd5fb0492fcab",
            "hr-831-1342.hd-190-2.ch-255-1334.lg-280-110.sh-290-92.ca-3485"
    );

    final Set<UserFriend> KNOWN_FRIENDS = Set.of(
            KOURIS_FRIEND,
            new UserFriend(
                    "sirjonasxx-VII",
                    "[PA] Bouwteam Wired Master ©",
                    false,
                    "hhnl-772b59c23bd0a1370172c3e4f3b37817",
                    "hr-3436-61-40.hd-180-1390.ch-3077-110-92.lg-280-110.ha-3242-110-92.ea-4986.fa-1206-110"
            ),
            new UserFriend(
                    "sirandya",
                    "— The star maker says it aint so bad —",
                    false,
                    "hhnl-5d8a8c2f0eac65f50ea859e104b804e0",
                    "hr-828-36.hd-209-1.ch-3030-92.lg-275-1320.sh-295-91.he-4022.ea-1401-110"
            )
    );

    final User KOURIS_USER = User.getByName(Hotel.NL, "Kouris");

    @Test
    void ofUniqueId() {
        Set<UserFriend> friends = UserFriend.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertEquals(212, friends.size());
        assertTrue(friends.containsAll(KNOWN_FRIENDS));
    }

    @Test
    void getAsUser() {
        assertEquals(KOURIS_USER, KOURIS_FRIEND.getAsUser());
    }
}