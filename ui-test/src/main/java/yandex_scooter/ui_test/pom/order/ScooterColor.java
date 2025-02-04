package yandex_scooter.ui_test.pom.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum ScooterColor {

    BLACK("#696969", "чёрный жемчуг"),
    GRAY("#696969", "серая безысходность");

    @Getter
    String hex;

    @Getter
    String name;

    public static ScooterColor valueOfName(String name) {
        for (ScooterColor color : values()) {
            if (color.getName().equals(name)) {
                return color;
            }
        }
        return null;
    }

    public static ScooterColor valueOfHex(String hex) {
        for (ScooterColor color : values()) {
            if (color.getHex().equals(hex)) {
                return color;
            }
        }
        return null;
    }
}
