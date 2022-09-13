package habbo.api.apipublic.users;

import habbo.api.apipublic.achievements.UserAchievement;
import habbo.api.apipublic.groups.Group;
import habbo.api.apipublic.groups.GroupMember;
import habbo.api.apipublic.rooms.DoorMode;
import habbo.api.apipublic.rooms.Room;
import habbo.api.hotel.Hotel;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRoomTest {
    static final DateTimeFormatter dtf_z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");

    final UserRoom TESTROOM_USER_ROOM = new UserRoom(
            34074758,
            "Testroom",
            "",
            LocalDateTime.parse("2012-04-06T18:13:10.000+00:00", dtf_z),
            "g-hhnl-7900aa48233542d676338b65b46e9359",
            Collections.emptySet(),
            75,
            true,
            "WiredSpast",
            "hhnl-f80ecffd2c8a4ffd46aa7786aa21feee",
            Collections.emptySet(),
            new URL("https://habbo-stories-content.s3.amazonaws.com/navigator-thumbnail/hhnl/34074758.png"),
            new URL("https://habbo-stories-content.s3.amazonaws.com/fullroom-photo/hhnl/34074758.png"),
            87,
            "r-hhnl-dfc79f5285b3e6c3d797b7ac9df86009"
    );

    final Set<UserRoom> KNOWN_ROOMS = Set.of(
            TESTROOM_USER_ROOM,
            new UserRoom(
                    39154011,
                    "test",
                    "",
                    LocalDateTime.parse("2020-10-05T11:26:45.000+00:00", dtf_z),
                    null,
                    Collections.emptySet(),
                    10,
                    true,
                    "WiredSpast",
                    "hhnl-f80ecffd2c8a4ffd46aa7786aa21feee",
                    Set.of("navigator.flatcategory.global.null"),
                    new URL("https://habbo-stories-content.s3.amazonaws.com/navigator-thumbnail/hhnl/39154011.png"),
                    new URL("https://habbo-stories-content.s3.amazonaws.com/fullroom-photo/hhnl/39154011.png"),
                    2,
                    "r-hhnl-fde081b3941ca156b6822ecc2d2163ba"
            ),
            new UserRoom(
                    39257007,
                    "testje",
                    "adewfreg",
                    LocalDateTime.parse("2021-09-23T19:33:54.000+00:00", dtf_z),
                    null,
                    Collections.emptySet(),
                    10,
                    true,
                    "WiredSpast",
                    "hhnl-f80ecffd2c8a4ffd46aa7786aa21feee",
                    Set.of("navigator.flatcategory.global.null"),
                    new URL("https://habbo-stories-content.s3.amazonaws.com/navigator-thumbnail/hhnl/39257007.png"),
                    new URL("https://habbo-stories-content.s3.amazonaws.com/fullroom-photo/hhnl/39257007.png"),
                    0,
                    "r-hhnl-a378a3b60df15824b51fb065231c0fa4"
            )
    );

    final Room TESTROOM_ROOM = new Room(
            34074758,
            "Testroom",
            "",
            LocalDateTime.parse("2012-04-06T18:13:10.000+00:00", dtf_z),
            "g-hhnl-7900aa48233542d676338b65b46e9359",
            Collections.emptySet(),
            75,
            true,
            "WiredSpast",
            "hhnl-f80ecffd2c8a4ffd46aa7786aa21feee",
            Collections.emptySet(),
            new URL("https://habbo-stories-content.s3.amazonaws.com/navigator-thumbnail/hhnl/34074758.png"),
            new URL("https://habbo-stories-content.s3.amazonaws.com/fullroom-photo/hhnl/34074758.png"),
            87,
            false,
            DoorMode.closed,
            "r-hhnl-dfc79f5285b3e6c3d797b7ac9df86009"
    );

    UserRoomTest() throws MalformedURLException {}

    @Test
    void ofUniqueId() {
        assertTrue(UserRoom.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee").containsAll(KNOWN_ROOMS));
    }

    @Test
    void asRoom() {
        assertEquals(TESTROOM_ROOM, TESTROOM_USER_ROOM.asRoom());
    }

    @Test
    void getOwner() {
        assertEquals(User.getByName(Hotel.NL, "WiredSpast"), TESTROOM_USER_ROOM.getOwner());
    }

    @Test
    void getOwnerProfile() {
        assertEquals(UserProfile.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM_USER_ROOM.getOwnerProfile());
    }

    @Test
    void getOwnerBadges() {
        assertEquals(UserBadge.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM_USER_ROOM.getOwnerBadges());
    }

    @Test
    void getOwnerFriends() {
        assertEquals(UserFriend.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM_USER_ROOM.getOwnerFriends());
    }

    @Test
    void getOwnerGroups() {
        assertEquals(UserGroup.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM_USER_ROOM.getOwnerGroups());
    }

    @Test
    void getOwnerAchievements() {
        assertEquals(UserAchievement.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM_USER_ROOM.getOwnerAchievements());
    }

    @Test
    void getOwnerRooms() {
        assertEquals(UserRoom.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM_USER_ROOM.getOwnerRooms());
    }

    @Test
    void getGroup() {
        assertEquals(Group.getByUniqueId("g-hhnl-7900aa48233542d676338b65b46e9359"), TESTROOM_USER_ROOM.getGroup());
    }

    @Test
    void getGroupMembers() {
        assertEquals(GroupMember.of("g-hhnl-7900aa48233542d676338b65b46e9359"), TESTROOM_USER_ROOM.getGroupMembers());
    }
}