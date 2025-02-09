package kz.yandex.scooter.pom.order;

import java.time.Duration;

public enum RentPeriod {

    ONE_DAY(Duration.ofDays(1), "сутки"),
    TWO_DAYS(Duration.ofDays(2), "двое суток"),
    THREE_DAYS(Duration.ofDays(3), "трое суток"),
    FOUR_DAYS(Duration.ofDays(4), "четверо суток"),
    FIVE_DAYS(Duration.ofDays(5), "пятеро суток"),
    SIX_DAYS(Duration.ofDays(6), "шестеро суток"),
    SEVEN_DAYS(Duration.ofDays(7), "семеро суток");

    private final Duration duration;
    private final String name;

    private RentPeriod(Duration duration, String name) {
        this.duration = duration;
        this.name = name;
    }

    public static RentPeriod valueOfDuration(Duration duration) {
        for (RentPeriod period : values()) {
            if (period.getDuration().equals(duration)) {
                return period;
            }
        }
        return null;
    }

    public static RentPeriod valueOfName(String name) {
        for (RentPeriod period : values()) {
            if (period.getName().equals(name)) {
                return period;
            }
        }
        return null;
    }

    public static RentPeriod random() {
        return values()[(int) (Math.random() * values().length)];
    }

    public Duration getDuration() {
        return this.duration;
    }

    public String getName() {
        return this.name;
    }
}
