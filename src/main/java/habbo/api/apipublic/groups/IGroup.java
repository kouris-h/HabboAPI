package habbo.api.apipublic.groups;

import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * Implements common functions of {@link Group} and {@link habbo.api.apipublic.users.UserGroup}
 *
 * @author WiredSpast
 * @version 2.0
 * @since 2.0
 */
public interface IGroup {
    String id();
    String name();
    String description();
    GroupType type();
    String roomId();
    String badgeCode();
    Color primaryColour();
    Color secondaryColour();

    /**
     * Returns the url to the image source of the groups badge
     *
     * @return URL of badge image source
     */
    default URL getBadgeUrl() {
        try {
            return new URL(String.format("https://www.habbo.com/habbo-imaging/badge/%s.gif", badgeCode()));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    /**
     * Fetches the members of the group
     *
     * @habbo.api /api/public/groups/&lt;{@link #id()}&gt;/members;
     * @return Set of members of the group
     */
    default Set<GroupMember> getMembers() {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/groups/%s/members", Hotel.fromId(id()), id()), GroupMember.class);
    }
}
