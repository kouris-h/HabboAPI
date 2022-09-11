package habbo.api.apipublic.users;

import habbo.api.apipublic.achievements.UserAchievement;

import java.util.List;
import java.util.Set;

public interface IUser {
    String name();
    String motto();
    String uniqueId();
    boolean online();

    default Set<UserFriend> getFriends() {
        return UserFriend.of(uniqueId());
    }

    default Set<UserGroup> getGroups() {
        return UserGroup.of(uniqueId());
    }

    default Set<UserBadge> getBadges() {
        return UserBadge.of(uniqueId());
    }

    default Set<UserAchievement> getAchievements() {
        return UserAchievement.of(uniqueId());
    }
}
