package habbo.api.apipublic.users;

import habbo.api.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.util.List;
import java.util.Set;

public record UserBadge(String code, String name, String description) {
    public static Set<UserBadge> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    public static Set<UserBadge> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/users/%s/badges", hotel, uniqueId), UserBadge.class);
    }
}
