package i18n;

import entity.Country;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @BeforeAll
    public static void  startAll() {
        System.out.println("Тест запущен");
    }

    @AfterAll
    public static void finishAll() {
        System.out.println("Тест выполнен");
    }

    @ParameterizedTest
    @MethodSource("source")
    public void localTest(Country country, String text) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(country);
        assertEquals(text, result);
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome"),
                Arguments.of(Country.USA, "Добро пожаловать"),
                Arguments.of(Country.RUSSIA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Добро пожаловать"),
                Arguments.of(Country.GERMANY, "Добро пожаловать"));
    }
}