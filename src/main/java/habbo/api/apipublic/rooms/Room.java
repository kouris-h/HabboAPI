package habbo.api.apipublic.rooms;

import habbo.api.apipublic.users.User;
import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents a room obtained from {@code /api/room/<id>}
 *
 * @author WiredSpast
 * @version 2.0
 * @since 1.0
 */
public record Room(int id, String name, String description, LocalDateTime creationTime, String habboGroupId,
                   Set<String> tags, int maximumVisitors, boolean showOwnerName, String ownerName,
                   String ownerUniqueId, Set<String> categories, URL thumbnailUrl, URL imageUrl,
                   int rating, boolean publicRoom, DoorMode doorMode, String uniqueId) implements IRoom {
    /**
     * Requests a room by its id and hotel
     *
     * @habbo.api /api/public/rooms/&lt;id&gt;
     * @param hotel hotel of the room you want to request
     * @param id unique id of the User you want to request
     * @return requested room (or {@code null} if room not found or invisible)
     */
    public static Room byId(Hotel hotel, int id) {
        return Fetcher.fetchObject(String.format("%s/api/public/rooms/%d", hotel, id), Room.class);
    }
}
