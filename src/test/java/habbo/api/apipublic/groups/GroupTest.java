package habbo.api.apipublic.groups;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {
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
    void getByUniqueId() {
        assertEquals(TESTROOM_GROUP, Group.getByUniqueId("g-hhnl-9d17ff1012d1d22501a7ea085edc8ab0"));
    }

    @Test
    void getBadgeUrl() throws MalformedURLException {
        assertEquals(new URL("https://www.habbo.com/habbo-imaging/badge/b07114s02014t47114s25014022ce93a6985e4d81710c32d67d42c63.gif"), TESTROOM_GROUP.getBadgeUrl());
    }

    @Test
    void getMembers() {
        assertEquals(GroupMember.of("g-hhnl-9d17ff1012d1d22501a7ea085edc8ab0"), TESTROOM_GROUP.getMembers());
    }
}