package sender;

import entity.Country;
import entity.Location;
import geo.GeoService;
import geo.GeoServiceImpl;
import i18n.LocalizationService;
import i18n.LocalizationServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @BeforeAll
    public static void  startAll() {
        System.out.println("Тесты запущены");
    }
    @AfterAll
    public static void finishAll() {
        System.out.println("\nТесты выполнены");
    }

    @DisplayName("Текст - RU, IP - RU")
    @Test
    void testSendRuIp() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.0.32.11"))
                .thenReturn(new Location("Moskow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        String expected = "Добро пожаловать";

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Текст - RU, IP - USA")
    @Test
    void testSendNotRuIp() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        String expected = "Добро пожаловать";

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Текст - USA, IP - USA")
    @Test
    void testSendUsaIp() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        String expected = "Welcome";

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Текст - USA, IP - RU")
    @Test
    void testSendNotUsaIp() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.0.32.11"))
                .thenReturn(new Location("Moskow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        String expected = "Welcome";

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }
}