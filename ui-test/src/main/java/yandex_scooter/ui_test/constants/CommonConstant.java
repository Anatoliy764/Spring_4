package yandex_scooter.ui_test.constants;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class CommonConstant {
    public static final Duration TIME_OUT = Duration.ofSeconds(5);
    public static final String URL_YANDEX_SCOOTER = "https://qa-scooter.praktikum-services.ru/";
    public static final String URL_YANDEX = "https://ya.ru/";

    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final String DATE_PICKER_PATTERN = "EEEE, d-е MMMM YYYY г.";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final DateTimeFormatter DATE_PICKER_FORMATTER = DateTimeFormatter.ofPattern(DATE_PICKER_PATTERN);

}
