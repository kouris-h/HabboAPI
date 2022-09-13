package habbo.api.hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    @Test
    void fromId() {
        assertEquals(Hotel.NL, Hotel.fromId("g-hhnl-5ae66633745"));
        assertEquals(Hotel.COM, Hotel.fromId("hhus-efwefwfewfwef"));
        assertEquals(Hotel.FR, Hotel.fromId("r-hhfr-9695585956"));
        assertNull(Hotel.fromId("g-hhen-9695585956"));
    }
}