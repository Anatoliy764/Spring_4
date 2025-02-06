package yandex_scooter.ui_test.constants;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

public final class CommonConstant {
    public static final Duration TIME_OUT = Duration.ofSeconds(10);
    public static final String URL_YANDEX_SCOOTER = "https://qa-scooter.praktikum-services.ru/";
    public static final String URL_YANDEX = "https://ya.ru/";

    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final String DATE_PICKER_PATTERN = "EEEE, d-е MMMM YYYY г.";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final DateTimeFormatter DATE_PICKER_FORMATTER = DateTimeFormatter.ofPattern(DATE_PICKER_PATTERN);

    private CommonConstant() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
