package habbo.api.apipublic.groups;

import habbo.api.apipublic.users.IUser;
import habbo.api.apipublic.users.User;
import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.Date;
import java.util.Set;

public record GroupMember(char gender, String motto, String habboFigure, Date memberSince, String uniqueId,
                          String name, boolean online, boolean isAdmin) implements IUser {
    public User getAsUser() {
        return User.getByUniqueId(uniqueId);
    }

    public static Set<GroupMember> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static Set<GroupMember> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/groups/%s/members", hotel, uniqueId), GroupMember.class);
    }
}
