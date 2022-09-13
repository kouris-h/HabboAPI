package habbo.api.apipublic.users;

import habbo.api.hotel.Hotel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {
    final User WIREDSPAST = User.getByName(Hotel.NL, "WiredSpast");

    final UserProfile WIREDSPAST_PROFILE = new UserProfile(
            WIREDSPAST,
            WIREDSPAST.getGroups(),
            WIREDSPAST.getBadges(),
            WIREDSPAST.getFriends(),
            WIREDSPAST.getRooms());

    @Test
    void ofUniqueId() {
        assertEquals(WIREDSPAST_PROFILE, UserProfile.of("hhnl-f80ecffd2c8a4ffd46aa7786aa21feee"));
    }
}