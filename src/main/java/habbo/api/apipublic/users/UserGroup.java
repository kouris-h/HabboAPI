package habbo.api.apipublic.users;

import habbo.api.apipublic.groups.IGroup;
import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.awt.*;
import java.util.List;

public record UserGroup(String id, String name, String description, String type, String roomId, String badgeCode, Color primaryColour, Color secondaryColour, boolean online, boolean isAdmin) implements IGroup {
    public static List<UserGroup> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static List<UserGroup> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectList(String.format("%s/api/public/users/%s/groups", hotel, uniqueId), UserGroup.class);
    }
}
