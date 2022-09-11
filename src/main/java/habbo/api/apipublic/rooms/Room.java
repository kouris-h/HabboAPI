package habbo.api.apipublic.rooms;

import habbo.api.apipublic.achievements.UserAchievement;
import habbo.api.apipublic.groups.Group;
import habbo.api.apipublic.groups.GroupMember;
import habbo.api.apipublic.users.*;
import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Set;

public record Room(int id, String name, String description, Date creationTime, String habboGroupId,
                   Set<String> tags, int maximumVisitors, boolean showOwnerName, String ownerName,
                   String ownerUniqueId, List<String> categories, URL thumbnailUrl, URL imageUrl,
                   int rating, DoorMode doorMode, String uniqueId) {
    public User getOwner() {
        return User.getByUniqueId(ownerUniqueId);
    }

    public UserProfile getOwnerProfile() {
        return UserProfile.of(ownerUniqueId);
    }

    public Set<UserBadge> getOwnerBadges() {
        return UserBadge.of(ownerUniqueId);
    }

    public Set<UserFriend> getOwnerFriends() {
        return UserFriend.of(ownerUniqueId);
    }

    public Set<UserGroup> getOwnerGroups() {
        return UserGroup.of(ownerUniqueId);
    }

    public Set<UserAchievement> getOwnerAchievements() {
        return UserAchievement.of(uniqueId);
    }

    public Set<Room> getOwnerRooms() {
        return Room.of(ownerUniqueId);
    }

    public Group getGroup() {
        return Group.getByUniqueId(habboGroupId);
    }

    public Set<GroupMember> getGroupMembers() {
        return GroupMember.of(habboGroupId);
    }

    public static Set<Room> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static Set<Room> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/users/%s/friends", hotel, uniqueId), Room.class);
    }
}
