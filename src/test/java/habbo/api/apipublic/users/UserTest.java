package habbo.api.apipublic.users;

import habbo.api.apipublic.achievements.UserAchievement;
import habbo.api.hotel.Hotel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    public static final DateTimeFormatter dtf_Z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    final User WIREDSPAST = new User(
            "hhnl-f80ecffd2c8a4ffd46aa7786aa21feee",
            "WiredSpast",
            "hr-828-1346.hd-180-1360.ch-3788-92.lg-3136-92.ea-3803-92.ca-4347-92-92",
            "",
            LocalDateTime.parse("2009-05-23T15:59:04.000+0000", dtf_Z),
            LocalDateTime.parse("2022-09-13T15:48:50.000+0000", dtf_Z),
            true,
            true,
            28,
            23,
            2665,
            5020,
            Set.of(
                    new User.SelectedBadge(1, "PT754", "Happy Pride 2019!", ""),
                    new User.SelectedBadge(2, "ES24I", "Ik ben een LGTBQ+ expert!", ""),
                    new User.SelectedBadge(3, "ES32I", "Liefde kent geen gender!", ""),
                    new User.SelectedBadge(4, "US113", "LGBTQIA+ Quiz Expert!", ""),
                    new User.SelectedBadge(5, "BSA23", "War of the Seasons - Boter kaas en eieren Winnaar", "")
            )
    );

    final UserProfile WIREDSPAST_PROFILE = new UserProfile(
            WIREDSPAST,
            WIREDSPAST.getGroups(),
            WIREDSPAST.getBadges(),
            WIREDSPAST.getFriends(),
            WIREDSPAST.getRooms());

    @Test
    void getByName() {
        User u = User.getByName(Hotel.NL, "WiredSpast");

        assertEquals(WIREDSPAST, u);
    }

    @Test
    void getByUniqueId() {
        User u = User.getByUniqueId("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertEquals(WIREDSPAST, u);
    }

    @Test
    void getProfile() {
        UserProfile p = User.getByName(Hotel.NL, "WiredSpast").getProfile();

        assertEquals(WIREDSPAST_PROFILE, p);
    }

    @Test
    void getFriends() {
        Set<UserFriend> friends = User.getByName(Hotel.NL, "WiredSpast").getFriends();
        Set<UserFriend> actualFriends = UserFriend.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertEquals(actualFriends, friends);
    }

    @Test
    void getGroups() {
        Set<UserGroup> groups = User.getByName(Hotel.NL, "WiredSpast").getGroups();
        Set<UserGroup> actualGroups = UserGroup.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertEquals(actualGroups, groups);
    }

    @Test
    void getBadges() {
        Set<UserBadge> badges = User.getByName(Hotel.NL, "WiredSpast").getBadges();
        Set<UserBadge> actualBadges = UserBadge.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertEquals(actualBadges, badges);
    }

    @Test
    void getRooms() {
        Set<UserRoom> rooms = User.getByName(Hotel.NL, "WiredSpast").getRooms();
        Set<UserRoom> actualRooms = UserRoom.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertEquals(actualRooms, rooms);
    }

    @Test
    void getAchievements() {
        Set<UserAchievement> achievements = User.getByName(Hotel.NL, "WiredSpast").getAchievements();
        Set<UserAchievement> actualAchievements = UserAchievement.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertEquals(actualAchievements, achievements);
    }

    @Test
    void getAsUserBadge() {
        Set<User.SelectedBadge> selectedBadges = User.getByName(Hotel.NL, "WiredSpast").selectedBadges();
        Set<UserBadge> asUserBadges = selectedBadges.stream().map(User.SelectedBadge::getAsUserBadge).collect(Collectors.toSet());


        assertEquals(Set.of(
                new UserBadge("PT754", "Happy Pride 2019!", ""),
                new UserBadge("ES24I", "Ik ben een LGTBQ+ expert!", ""),
                new UserBadge("ES32I", "Liefde kent geen gender!", ""),
                new UserBadge("US113", "LGBTQIA+ Quiz Expert!", ""),
                new UserBadge("BSA23", "War of the Seasons - Boter kaas en eieren Winnaar", "")
        ), asUserBadges);
    }
}