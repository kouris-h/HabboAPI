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

public record Room(int id, String name, String description, Date creationTime, String habboGroupId,
                   List<String> tags, int maximumVisitors, boolean showOwnerName, String ownerName,
                   String ownerUniqueId, List<String> categories, URL thumbnailUrl, URL imageUrl,
                   int rating, DoorMode doorMode, String uniqueId) {
    public User getOwner() {
        return User.getByUniqueId(ownerUniqueId);
    }

    public UserProfile getOwnerProfile() {
        return UserProfile.of(ownerUniqueId);
    }

    public List<UserBadge> getOwnerBadges() {
        return UserBadge.of(ownerUniqueId);
    }

    public List<UserFriend> getOwnerFriends() {
        return UserFriend.of(ownerUniqueId);
    }

    public List<UserGroup> getOwnerGroups() {
        return UserGroup.of(ownerUniqueId);
    }

    public List<UserAchievement> getOwnerAchievements() {
        return UserAchievement.of(uniqueId);
    }

    public List<Room> getOwnerRooms() {
        return Room.of(ownerUniqueId);
    }

    public Group getGroup() {
        return Group.getByUniqueId(habboGroupId);
    }

    public List<GroupMember> getGroupMembers() {
        return GroupMember.of(habboGroupId);
    }

    public static List<Room> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static List<Room> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectList(String.format("%s/api/public/users/%s/friends", hotel, uniqueId), Room.class);
    }
}
