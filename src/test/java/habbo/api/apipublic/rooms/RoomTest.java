package habbo.api.apipublic.rooms;

import habbo.api.apipublic.achievements.UserAchievement;
import habbo.api.apipublic.groups.Group;
import habbo.api.apipublic.groups.GroupMember;
import habbo.api.apipublic.users.*;
import habbo.api.hotel.Hotel;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    static final DateTimeFormatter dtf_z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");

    final Room TESTROOM = new Room(
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

    RoomTest() throws MalformedURLException {}

    @Test
    void byId() {
        assertEquals(TESTROOM, Room.byId(Hotel.NL, 34074758));
    }

    @Test
    void getOwner() {
        assertEquals(User.getByName(Hotel.NL, "WiredSpast"), TESTROOM.getOwner());
    }

    @Test
    void getOwnerProfile() {
        assertEquals(UserProfile.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM.getOwnerProfile());
    }

    @Test
    void getOwnerBadges() {
        assertEquals(UserBadge.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM.getOwnerBadges());
    }

    @Test
    void getOwnerFriends() {
        assertEquals(UserFriend.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM.getOwnerFriends());
    }

    @Test
    void getOwnerGroups() {
        assertEquals(UserGroup.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM.getOwnerGroups());
    }

    @Test
    void getOwnerAchievements() {
        assertEquals(UserAchievement.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM.getOwnerAchievements());
    }

    @Test
    void getOwnerRooms() {
        assertEquals(UserRoom.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"), TESTROOM.getOwnerRooms());
    }

    @Test
    void getGroup() {
        assertEquals(Group.getByUniqueId("g-hhnl-7900aa48233542d676338b65b46e9359"), TESTROOM.getGroup());
    }

    @Test
    void getGroupMembers() {
        assertEquals(GroupMember.of("g-hhnl-7900aa48233542d676338b65b46e9359"), TESTROOM.getGroupMembers());
    }
}