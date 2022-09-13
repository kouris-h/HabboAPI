package habbo.api.apipublic.groups;

import habbo.api.apipublic.achievements.UserAchievement;
import habbo.api.apipublic.users.*;
import habbo.api.hotel.Hotel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GroupMemberTest {
    static final DateTimeFormatter dtf_z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");

    static final GroupMember WIREDSPAST = new GroupMember(
            'm',
            "",
            "hr-828-1346.hd-180-1360.ch-3788-92.lg-3136-92.ea-3803-92.ca-4347-92-92",
            LocalDateTime.parse("2009-05-23T15:59:04.000+00:00", dtf_z),
            "hhnl-f80ecffd2c8a4ffd46aa7786aa21feee",
            "WiredSpast",
            true,
            true
    );

    static final Set<GroupMember> KNOWN_MEMBERS = Set.of(
            WIREDSPAST,
            new GroupMember(
                    'f',
                    "Je bent super leuk| Hou van je xo Yous",
                    "hr-3012-1346.hd-605-1360.ch-3788-92.lg-3136-106.sh-725-92.ha-3709.ea-3107-92-92.ca-3187-92",
                    LocalDateTime.parse("2012-11-22T11:31:32.000+00:00", dtf_z),
                    "hhnl-87d7cb3834568f08f910318b2b757882",
                    "iSpast",
                    false,
                    true
            )
    );

    @Test
    void ofUniqueId() {
        assertTrue(GroupMember.of("g-hhnl-9d17ff1012d1d22501a7ea085edc8ab0").containsAll(KNOWN_MEMBERS));
    }

    @Test
    void getAsUser() {
        assertEquals(User.getByName(Hotel.NL, "WiredSpast"), WIREDSPAST.getAsUser());
    }

    @Test
    void getProfile() {
        assertEquals(UserProfile.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), WIREDSPAST.getProfile());
    }

    @Test
    void getFriends() {
        assertEquals(UserFriend.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), WIREDSPAST.getFriends());
    }

    @Test
    void getGroups() {
        assertEquals(UserGroup.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), WIREDSPAST.getGroups());
    }

    @Test
    void getBadges() {
        assertEquals(UserBadge.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), WIREDSPAST.getBadges());
    }

    @Test
    void getRooms() {
        assertEquals(UserRoom.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), WIREDSPAST.getRooms());
    }

    @Test
    void getAchievements() {
        assertEquals(UserAchievement.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), WIREDSPAST.getAchievements());
    }
}