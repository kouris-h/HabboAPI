package habbo.api.apipublic.groups;

import habbo.api.apipublic.users.User;
import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.awt.*;

public record Group(String id, String name, String description, String type, String roomId, String badgeCode,
                    Color primaryColour, Color secondaryColour) implements IGroup {
    public static Group getByUniqueId(String uniqueId) {
        return getByUniqueId(Hotel.fromId(uniqueId), uniqueId);
    }

    public static Group getByUniqueId(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObject(String.format("%s/api/public/groups/%s", hotel, uniqueId), Group.class);
    }

    public static void main(String[] args) {
        User u = User.getByName(Hotel.NL, "WiredSpast");
        System.out.println(u.getGroups());
    }
}
