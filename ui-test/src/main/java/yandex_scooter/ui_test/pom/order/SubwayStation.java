package yandex_scooter.ui_test.pom.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SubwayStation {

    БУЛЬВАР_РОКОССОВСКОГО(1, "Бульвар Рокоссовского", "#D92B2C"),
    РЕЧНОЙ_ВОКЗАЛ(23, "Речной вокзал", "#4DBE52"),
    ПЯТНИЦКОЕ_ШОССЕ(45, "Пятницкое шоссе", "#2C75C4"),
    КУНЦЕВСКАЯ(67, "Кунцевская", "#4DC6F4"),
    МЕДВЕДКОВО(92, "Медведково", "#F07025"),
    ПЛАНЕРНАЯ(116, "Планерная", "#89339E"),
    РАМЕНКИ(139, "Раменки", "#FBC81E"),
    АЛТУФЬЕВО(152, "Алтуфьево", "#9F9F9F"),
    ПЕТРОВСКО_РАЗУМОВСКАЯ(177, "Алтуфьево", "#A8D92D"),
    КАШИРСКАЯ(197, "Каширская", "#80D4C9"),
    БИТЦЕВСКИЙ_ПАРК(200, "Битцевский парк", "#B0BFE7"),
    ОКРУЖНАЯ(207, "Окружная", "#efadb5"),
    ;

    int number;
    String name;
    String color;

    public static SubwayStation valueOfName(String name) {
        for (SubwayStation station : values()) {
            if (station.getName().equals(name)) {
                return station;
            }
        }
        return null;
    }

    public static SubwayStation random() {
        return values()[(int) (Math.random() * values().length)];
    }
}
