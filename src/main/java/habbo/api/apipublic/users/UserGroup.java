package habbo.api.apipublic.users;

import habbo.api.apipublic.groups.Group;
import habbo.api.apipublic.groups.GroupType;
import habbo.api.apipublic.groups.IGroup;
import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.awt.Color;
import java.util.Set;

/**
 * Represents a group obtained from {@code /api/users/<uniqueId>/groups}
 *
 * @author WiredSpast
 * @version 2.0
 * @since 1.0
 */
public record UserGroup(String id, String name, String description, GroupType type, String roomId,
                        String badgeCode, Color primaryColour, Color secondaryColour,
                        boolean online, boolean isAdmin) implements IGroup {
    /**
     * Get the user group as a {@link Group} instance
     * @return {@link Group} instance of the user group
     */
    public Group asGroup() {
        return new Group(id, name, description, type, roomId, badgeCode, primaryColour, secondaryColour);
    }

    /**
     * Requests all groups of which a user is a member by their unique id
     *
     * @habbo.api {@code /api/public/users/<uniqueId>/groups}
     * @param uniqueId unique id of the user
     * @return Set of requested groups (or {@code null} if user not found or private)
     * @implNote Extracts the hotel from the unique id and calls {@link #of(Hotel, String)} )}
     */
    public static Set<UserGroup> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    /**
     * Requests all groups of which a user is a member by their unique id and their hotel
     *
     * @habbo.api {@code /api/public/users/<uniqueId>/groups}
     * @param hotel hotel of the user
     * @param uniqueId unique id of the user
     * @return Set of requested groups (or {@code null} if user not found or private)
     */
    public static Set<UserGroup> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/users/%s/groups", hotel, uniqueId), UserGroup.class);
    }
}
