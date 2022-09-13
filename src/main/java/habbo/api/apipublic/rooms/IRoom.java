package habbo.api.apipublic.rooms;

import habbo.api.apipublic.achievements.UserAchievement;
import habbo.api.apipublic.groups.Group;
import habbo.api.apipublic.groups.GroupMember;
import habbo.api.apipublic.users.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Implements common functions of {@link Room} and {@link UserRoom}
 *
 * @author WiredSpast
 * @version 2.0
 * @since 2.0
 */
public interface IRoom {
    int id();
    String name();
    String description();
    LocalDateTime creationTime();
    String habboGroupId();
    Set<String> tags();
    int maximumVisitors();
    boolean showOwnerName();
    String ownerName();
    String ownerUniqueId();
    Set<String> categories();
    URL thumbnailUrl();
    URL imageUrl();
    int rating();
    String uniqueId();

    /**
     * Fetches the user of the room owner
     *
     * @habbo.api /api/public/users/&lt;{@link #ownerUniqueId()}&gt;
     * @return User of the room owner
     */
    default User getOwner() {
        return User.getByUniqueId(ownerUniqueId());
    }

    /**
     * Fetches the full profile of the room owner
     *
     * @habbo.api /api/public/users/&lt;{@link #ownerUniqueId()}&gt;/profile;
     * @return Profile of the room owner (or null if the owner's profile is private)
     */
    default UserProfile getOwnerProfile() {
        return UserProfile.of(ownerUniqueId());
    }

    /**
     * Fetches the badges of the room owner
     *
     * @habbo.api /api/public/users/&lt;{@link #ownerUniqueId()}&gt;/badges;
     * @return Set of badges of the room owner (or null if the owner's profile is private)
     */
    default Set<UserBadge> getOwnerBadges() {
        return UserBadge.of(ownerUniqueId());
    }

    /**
     * Fetches the friends of the room owner
     *
     * @habbo.api /api/public/users/&lt;{@link #ownerUniqueId()}&gt;/friends;
     * @return Set of friends of the room owner (or null if the owner's profile is private)
     */
    default Set<UserFriend> getOwnerFriends() {
        return UserFriend.of(ownerUniqueId());
    }

    /**
     * Fetches the groups of the room owner
     *
     * @habbo.api /api/public/users/&lt;{@link #ownerUniqueId()}&gt;/groups;
     * @return Set of groups of the room owner (or null if the owner's profile is private)
     */
    default Set<UserGroup> getOwnerGroups() {
        return UserGroup.of(ownerUniqueId());
    }

    /**
     * Fetches the achievements of the room owner
     *
     * @habbo.api /api/public/achievements/&lt;{@link #ownerUniqueId()}&gt;
     * @return Set of achievements of the room owner
     */
    default Set<UserAchievement> getOwnerAchievements() {
        return UserAchievement.of(uniqueId());
    }

    /**
     * Fetches the rooms of the room owner
     *
     * @habbo.api /api/public/users/&lt;{@link #ownerUniqueId()}&gt;/rooms;
     * @return Set of rooms of the room owner (or null if the owner's profile is private)
     */
    default Set<UserRoom> getOwnerRooms() {
        return UserRoom.of(ownerUniqueId());
    }

    /**
     * Fetches the group of the room
     *
     * @habbo.api /api/public/groups/&lt;{@link #habboGroupId()}&gt;/members;
     * @return Group of the room (or null if the room has no group)
     */
    default Group getGroup() {
        return habboGroupId() != null ? Group.getByUniqueId(habboGroupId()) : null;
    }

    /**
     * Fetches the members of the room's group
     *
     * @habbo.api /api/public/groups/&lt;{@link #habboGroupId()}&gt;/members;
     * @return Set of members of the room's group (or null if the room has no group)
     */
    default Set<GroupMember> getGroupMembers() {
        return habboGroupId() != null ? GroupMember.of(habboGroupId()) : null;
    }
}
