package habbo.api.apipublic.users;

import habbo.api.apipublic.groups.IGroup;
import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.awt.Color;
import java.util.Set;

public record UserGroup(String id, String name, String description, String type, String roomId, String badgeCode, Color primaryColour, Color secondaryColour, boolean online, boolean isAdmin) implements IGroup {
    public static Set<UserGroup> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static Set<UserGroup> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/users/%s/groups", hotel, uniqueId), UserGroup.class);
    }
}
