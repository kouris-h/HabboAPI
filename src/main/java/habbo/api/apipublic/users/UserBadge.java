package habbo.api.apipublic.users;

import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.List;

public record UserBadge(String code, String name, String description) {
    public static List<UserBadge> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static List<UserBadge> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectList(String.format("%s/api/public/users/%s/badges", hotel, uniqueId), UserBadge.class);
    }
}
