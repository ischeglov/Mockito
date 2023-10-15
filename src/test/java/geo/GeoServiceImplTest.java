package geo;

import entity.Country;
import entity.Location;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @BeforeAll
    public static void  startAll() {
        System.out.println("Тест запущен");
    }

    @AfterAll
    public static void finishAll() {
        System.out.println("Тест выполнен");
    }

    GeoService geoServiceImpl = new GeoServiceImpl();
    @ParameterizedTest
    @MethodSource("source")
    public void testByIp(String ip, Location expected) {
        Location location = geoServiceImpl.byIp(ip);
        assertEquals(expected.getCity(), location.getCity());
        assertEquals(expected.getCountry(), location.getCountry());
        assertEquals(expected.getStreet(), location.getStreet());
        assertEquals(expected.getBuiling(), location.getBuiling());

    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("96.44.183.149", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("172.0.32.11", new Location("New York", Country.USA, " 10th Avenue", 32)));
    }
}