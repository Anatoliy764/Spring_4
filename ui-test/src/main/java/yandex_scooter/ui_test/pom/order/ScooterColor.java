package yandex_scooter.ui_test.pom.order;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum ScooterColor {

    BLACK("#696969", "чёрный жемчуг"),
    GRAY("#696969", "серая безысходность");

    private final
    String hex;

    private final
    String name;

    private ScooterColor(String hex, String name) {
        this.hex = hex;
        this.name = name;
    }

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

    public String getHex() {
        return this.hex;
    }

    public String getName() {
        return this.name;
    }
}
