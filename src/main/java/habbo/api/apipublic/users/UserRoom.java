package habbo.api.apipublic.users;

import habbo.api.apipublic.rooms.IRoom;
import habbo.api.apipublic.rooms.Room;
import habbo.api.util.fetch.Fetcher;
import habbo.api.hotel.Hotel;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents a room obtained from {@code /api/users/<uniqueId>/rooms}
 *
 * @author WiredSpast
 * @version 2.0
 * @since 1.0
 */
public record UserRoom(int id, String name, String description, LocalDateTime creationTime,
                       String habboGroupId, Set<String> tags, int maximumVisitors, boolean showOwnerName,
                       String ownerName, String ownerUniqueId, Set<String> categories, URL thumbnailUrl,
                       URL imageUrl, int rating, String uniqueId) implements IRoom {
    /**
     * Get the user room as a {@link Room} instance
     * @return {@link Room} instance of the user room
     */
    public Room asRoom() {
        return Room.byId(Hotel.fromId(uniqueId), id);
    }
    /**z
     * Requests the rooms of a user by their unique id
     *
     * @habbo.api {@code /api/public/users/<uniqueId>/rooms}
     * @param uniqueId unique id of the user
     * @return Set of requested rooms (or {@code null} if user not found or private)
     * @implNote Extracts the hotel from the unique id and calls {@link UserRoom#of(Hotel, String)}
     */
    public static Set<UserRoom> of(String uniqueId) {
        return of(Hotel.fromId(uniqueId), uniqueId);
    }

    /**
     * Requests the rooms of a user by their unique id and their hotel
     *
     * @habbo.api {@code /api/public/users/<uniqueId>/rooms}
     * @param hotel hotel of the user
     * @param uniqueId unique id of the user
     * @return Set of requested rooms (or {@code null} if user not found or private)
     */
    public static Set<UserRoom> of(Hotel hotel, String uniqueId) {
        return Fetcher.fetchObjectSet(String.format("%s/api/public/users/%s/rooms", hotel, uniqueId), UserRoom.class);
    }
}
