package habbo.api.apipublic.groups;

import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public interface IGroup {
    String id();
    String name();
    String description();
    String type();
    String roomId();
    String badgeCode();
    Color primaryColour();
    Color secondaryColour();

    default URL getBadgeUrl() {
        try {
            return new URL(String.format("https://www.habbo.com/habbo-imaging/badge/%s.gif", badgeCode()));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    default List<GroupMember> getMembers() {
        return Fetcher.fetchObjectList(String.format("%s/api/public/groups/%s/members", Hotel.fromId(id()), id()), GroupMember.class);
    }
}
