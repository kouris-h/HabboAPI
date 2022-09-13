package habbo.api.apipublic.users;

import habbo.api.apipublic.achievements.UserAchievement;

import java.util.Set;

/**
 * Implements common functions of {@link User}, {@link UserFriend} and {@link habbo.api.apipublic.groups.GroupMember}
 *
 * @author WiredSpast
 * @version 2.0
 * @since 2.0
 */
public interface IUser {
    String name();
    String motto();
    String uniqueId();
    boolean online();

    /**
     * Get full profile of the user (includes user, groups, badges, friends and rooms)
     *
     * @habbo.api /api/public/users/&lt;{@link #uniqueId()}&gt;/profile}
     * @return The full profile of the user (includes user, friends, groups, badges and rooms)
     */
    default UserProfile getProfile() {
        return UserProfile.of(uniqueId());
    }

    /**
     * Get friends of the user
     *
     * @habbo.api /api/public/users/&lt;{@link #uniqueId()}&gt;/friends
     * @return A set containing the friends of the user
     */
    default Set<UserFriend> getFriends() {
        return UserFriend.of(uniqueId());
    }

    /**
     * Get groups of which the user is a member
     *
     * @habbo.api /api/public/users/&lt;{@link #uniqueId()}&gt;/groups
     * @return A set containing the groups of which the user is a member
     */
    default Set<UserGroup> getGroups() {
        return UserGroup.of(uniqueId());
    }

    /**
     * Get badges which the user has
     *
     * @habbo.api /api/public/users/&lt;{@link #uniqueId()}&gt;/badges
     * @return A set containing the badges which the user has
     */
    default Set<UserBadge> getBadges() {
        return UserBadge.of(uniqueId());
    }

    /**
     * Get rooms of the user
     *
     * @habbo.api api/public/users/&lt;{@link #uniqueId()}&gt;/rooms
     * @return A set containing the rooms of the user
     */
    default Set<UserRoom> getRooms() {
        return UserRoom.of(uniqueId());
    }

    /**
     * Get achievements the user has achieved
     *
     * @habbo.api /api/public/achievements/&lt;{@link #uniqueId()}&gt;
     * @return A set containing the achievements the user has achieved
     */
    default Set<UserAchievement> getAchievements() {
        return UserAchievement.of(uniqueId());
    }
}
