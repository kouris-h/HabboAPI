package habbo.api.apipublic.users;

import habbo.api.apipublic.achievements.UserAchievement;

import java.util.List;

public interface IUser {
    String name();
    String motto();
    String uniqueId();
    boolean online();

    default List<UserFriend> getFriends() {
        return UserFriend.of(uniqueId());
    }

    default List<UserGroup> getGroups() {
        return UserGroup.of(uniqueId());
    }

    default List<UserBadge> getBadges() {
        return UserBadge.of(uniqueId());
    }

    default List<UserAchievement> getAchievements() {
        return UserAchievement.of(uniqueId());
    }
}
