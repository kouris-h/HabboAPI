package habbo.api.apipublic.users;

import habbo.api.apipublic.groups.Group;
import habbo.api.apipublic.groups.GroupMember;
import habbo.api.apipublic.groups.GroupType;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserGroupTest {
    final UserGroup TESTROOM_USER_GROUP = new UserGroup(
            "g-hhnl-9d17ff1012d1d22501a7ea085edc8ab0",
            "Testroom",
            "Tester",
            GroupType.EXCLUSIVE,
            "r-hhnl-960a903232ce0590df1c06f470729e5c",
            "b07114s02014t47114s25014022ce93a6985e4d81710c32d67d42c63",
            new Color(0x417200),
            new Color(0x00539b),
            false,
            true
    );

    final Set<UserGroup> KNOWN_USER_GROUPS = Set.of(
            TESTROOM_USER_GROUP,
            new UserGroup(
                    "g-hhnl-236d7b6b1ea7ac39da231af084704b17",
                    "Het Eenrichtingsspel - Leden!",
                    "Dit is de officiële ledengroep van het Eenrichtingsspel dat in samenwerking is met ::::tamara::::. 12000+ leden bereikt! Bedankt spelers! :-)",
                    GroupType.NORMAL,
                    "r-hhnl-ed66db4a85c73082a7dbb887e70f288e",
                    "b21164s08057s91112s78111s831105d85c81c1cc4271a747c81c38df4e49f",
                    new Color(0xffffff),
                    new Color(0x4c4c4c),
                    false,
                    false
            ),
            new UserGroup(
                    "g-hhnl-411381ffcf7b3a63348835b043820295",
                    "RUN",
                    "",
                    GroupType.CLOSED,
                    "r-hhnl-9ab493847860b4f28ab0b93551cd3b88",
                    "b07134t48244s20021s06124t5709414c947a9902bc2f1faa7fdfbaac8c81c",
                    new Color(0xffffff),
                    new Color(0xffffff),
                    false,
                    true
            )
    );

    final Group TESTROOM_GROUP = new Group(
            "g-hhnl-9d17ff1012d1d22501a7ea085edc8ab0",
            "Testroom",
            "Tester",
            GroupType.EXCLUSIVE,
            "r-hhnl-960a903232ce0590df1c06f470729e5c",
            "b07114s02014t47114s25014022ce93a6985e4d81710c32d67d42c63",
            new Color(0x417200),
            new Color(0x00539b)
    );

    @Test
    void ofUniqueId() {
        Set<UserGroup> groups = UserGroup.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee");

        assertTrue(groups.containsAll(KNOWN_USER_GROUPS));
    }

    @Test
    void asGroup() {
        assertEquals(TESTROOM_GROUP, TESTROOM_USER_GROUP.asGroup());
    }

    @Test
    void getBadgeUrl() throws MalformedURLException {
        assertEquals(new URL("https://www.habbo.com/habbo-imaging/badge/b07114s02014t47114s25014022ce93a6985e4d81710c32d67d42c63.gif"), TESTROOM_USER_GROUP.getBadgeUrl());
    }

    @Test
    void getMembers() {
        assertEquals(GroupMember.of("g-hhnl-9d17ff1012d1d22501a7ea085edc8ab0"), TESTROOM_USER_GROUP.getMembers());
    }
}