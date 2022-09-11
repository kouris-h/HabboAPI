package habbo.api.apipublic.users;

import habbo.api.hotel.Hotel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static habbo.api.util.deserialize.LocalDateTimeDeserializer.dtf;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void getByName() {
        User u = User.getByName(Hotel.NL, "WiredSpast");

        assertWiredSpast(u);
    }

    @Test
    void getByUniqueId() {
        User u = User.getByUniqueId("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertWiredSpast(u);
    }

    @Test
    void getFriends() {
        List<UserFriend> friends = User.getByName(Hotel.NL, "WiredSpast").getFriends();
        List<UserFriend> actualFriends = UserFriend.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertEquals(212, friends.size());
        assertEquals(actualFriends, friends);
    }

    void assertWiredSpast(User u) {
        assertEquals("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee", u.uniqueId());
        assertEquals("WiredSpast", u.name());
        assertEquals("hr-828-1346.hd-180-1360.ch-3788-92.lg-3136-92.ea-3803-92.ca-4347-92-92", u.figureString());
        assertEquals("", u.motto());
        assertTrue(u.online());
        assertEquals(LocalDateTime.parse("2022-09-11T09:39:12.000+0000", dtf), u.lastAccessTime());
        assertEquals(LocalDateTime.parse("2009-05-23T15:59:04.000+0000", dtf), u.memberSince());
        assertTrue(u.profileVisible());
        assertEquals(28, u.currentLevel());
        assertEquals(23, u.currentLevelCompletePercent());
        assertEquals(2665, u.totalExperience());
        assertEquals(5020, u.starGemCount());
        assertEquals(List.of(
                new User.SelectedBadge(1, "PT754", "Happy Pride 2019!", ""),
                new User.SelectedBadge(2, "ES24I", "Ik ben een LGTBQ+ expert!", ""),
                new User.SelectedBadge(3, "ES32I", "Liefde kent geen gender!", ""),
                new User.SelectedBadge(4, "US113", "LGBTQIA+ Quiz Expert!", ""),
                new User.SelectedBadge(5, "KPP26", "Habbo Pride Stad", "")
        ), u.selectedBadges());
    }
}