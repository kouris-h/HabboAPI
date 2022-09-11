package habbo.api.apipublic.groups;

import habbo.api.apipublic.users.IUser;
import habbo.api.apipublic.users.User;
import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.Date;
import java.util.List;

public record GroupMember(char gender, String motto, String habboFigure, Date memberSince, String uniqueId,
                          String name, boolean online, boolean isAdmin) implements IUser {
    public User getAsUser() {
        return User.getByUniqueId(uniqueId);
    }

    public static List<GroupMember> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static List<GroupMember> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectList(String.format("%s/api/public/groups/%s/members", hotel, uniqueId), GroupMember.class);
    }
}
