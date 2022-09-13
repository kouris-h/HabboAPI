package habbo.api.fetch;

import habbo.api.util.fetch.Fetcher;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FetcherTest {
    protected static final DateTimeFormatter dtf_Z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    final Tester WIREDSPAST = new Tester(
            "WiredSpast",
            23,
            LocalDateTime.parse("2018-11-30T23:16:08.000+0000", dtf_Z),
            TestRole.DEVELOPER,
            new Color(0x84c3c1)
    );

    final Tester[] TESTERS = new Tester[] {
            WIREDSPAST,
            new Tester(
                    "Kouris",
                    12,
                    LocalDateTime.parse("2010-01-17T06:14:50.000+0000", dtf_Z),
                    TestRole.SCRIPTER,
                    new Color(0x3b2411)
            ),
            new Tester(
                    "sirjonasxx",
                    40,
                    LocalDateTime.parse("1982-08-02T13:44:27.000+0000", dtf_Z),
                    TestRole.PLAYER,
                    new Color(0x620c6d)
            ),
    };

    @Test
    void fetchObject() {
        assertEquals(WIREDSPAST,
                Fetcher.fetchObject(
                        "https://raw.githubusercontent.com/kouris-h/HabboAPI/reformat/src/test/resources/fetch_object_test.json",
                        Tester.class
                )
        );
    }

    @Test
    void fetchObjectError() {
        // wrong class
        assertNull(
                Fetcher.fetchObject(
                        "https://raw.githubusercontent.com/kouris-h/HabboAPI/reformat/src/test/resources/fetch_object_test.json",
                        FetcherTest.class
                )
        );
        // invalid url
        assertNull(
                Fetcher.fetchObject(
                        "https://raw.githubusercontent.co/kouris-h/HabboAPI/reformat/src/test/resources/fetch_object_test.json",
                        Tester.class
                )
        );
        // 404
        assertNull(
                Fetcher.fetchObject(
                        "https://raw.githubusercontent.co/kouris-h/HabboAPI/reformat/src/test/resources/doesnt_exist.json",
                        Tester.class
                )
        );
    }

    @Test
    void fetchObjectArray() {
        assertArrayEquals(TESTERS,
                Fetcher.fetchObjectArray(
                        "https://raw.githubusercontent.com/kouris-h/HabboAPI/reformat/src/test/resources/fetch_collection_test.json",
                        Tester[].class
                )
        );
    }

    @Test
    void fetchObjectArrayError() {
        // wrong class
        assertNull(
                Fetcher.fetchObjectArray(
                        "https://raw.githubusercontent.com/kouris-h/HabboAPI/reformat/src/test/resources/fetch_object_test.json",
                        FetcherTest[].class
                )
        );
        // invalid url
        assertNull(
                Fetcher.fetchObjectArray(
                        "https://raw.githubusercontent.co/kouris-h/HabboAPI/reformat/src/test/resources/fetch_object_test.json",
                        Tester[].class
                )
        );
        // 404
        assertNull(
                Fetcher.fetchObjectArray(
                        "https://raw.githubusercontent.co/kouris-h/HabboAPI/reformat/src/test/resources/doesnt_exist.json",
                        Tester[].class
                )
        );
    }

    @Test
    void fetchObjectList() {
        assertEquals(List.of(TESTERS),
                Fetcher.fetchObjectList(
                        "https://raw.githubusercontent.com/kouris-h/HabboAPI/reformat/src/test/resources/fetch_collection_test.json",
                        Tester.class
                )
        );
    }

    @Test
    void fetchObjectListError() {
        // wrong class
        assertNull(
                Fetcher.fetchObjectList(
                        "https://raw.githubusercontent.com/kouris-h/HabboAPI/reformat/src/test/resources/fetch_object_test.json",
                        FetcherTest.class
                )
        );
        // invalid url
        assertNull(
                Fetcher.fetchObjectList(
                        "https://raw.githubusercontent.co/kouris-h/HabboAPI/reformat/src/test/resources/fetch_object_test.json",
                        Tester.class
                )
        );
        // 404
        assertNull(
                Fetcher.fetchObjectList(
                        "https://raw.githubusercontent.co/kouris-h/HabboAPI/reformat/src/test/resources/doesnt_exist.json",
                        Tester.class
                )
        );
    }

    @Test
    void fetchObjectSet() {
        assertEquals(Set.of(TESTERS),
                Fetcher.fetchObjectSet(
                        "https://raw.githubusercontent.com/kouris-h/HabboAPI/reformat/src/test/resources/fetch_collection_test.json",
                        Tester.class
                )
        );
    }

    @Test
    void fetchObjectSetError() {
        // wrong class
        assertNull(
                Fetcher.fetchObjectSet(
                        "https://raw.githubusercontent.com/kouris-h/HabboAPI/reformat/src/test/resources/fetch_object_test.json",
                        FetcherTest.class
                )
        );
        // invalid url
        assertNull(
                Fetcher.fetchObjectSet(
                        "https://raw.githubusercontent.co/kouris-h/HabboAPI/reformat/src/test/resources/fetch_object_test.json",
                        Tester.class
                )
        );
        // 404
        assertNull(
                Fetcher.fetchObjectSet(
                        "https://raw.githubusercontent.co/kouris-h/HabboAPI/reformat/src/test/resources/doesnt_exist.json",
                        Tester.class
                )
        );
    }

    public record Tester(String name, int age, LocalDateTime randomDateTime, TestRole mainRole, Color color) {}

    public enum TestRole {
        DEVELOPER,
        SCRIPTER,
        PLAYER
    }
}