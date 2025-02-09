package kz.yandex.scooter.pom.home;

import java.util.Arrays;
import java.util.Objects;

public enum FaqEnum {

    PRICE_AND_PAYMENT(
            "Сколько это стоит? И как оплатить?",
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
    ),
    RENT_MULTIPLE_SCOOTERS(
            "Хочу сразу несколько самокатов! Так можно?",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
    ),
    RENTAL_TIME_CALCULATION(
            "Как рассчитывается время аренды?",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."
    ),
    RENT_SCOOTER_FOR_TODAY(
            "Можно ли заказать самокат прямо на сегодня?",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
    ),
    ORDER_EDIT(
            "Можно ли продлить заказ или вернуть самокат раньше?",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."
    ),
    SCOOTER_WITH_CHARGER(
            "Вы привозите зарядку вместе с самокатом?",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."
    ),
    CANCEL_ORDER(
            "Можно ли отменить заказ?",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."
    ),
    ORDER_AREA(
            "Я живу за МКАДом, привезёте?",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    );

    final String question;
    final String answer;

    private FaqEnum(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public static FaqEnum valueOf(int ordinal) {
        return values()[ordinal];
    }

    public static FaqEnum valueOfQuestion(String question) {
        return Arrays.stream(FaqEnum.values())
                .filter(faq -> Objects.equals(faq.question, question))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Нет вопроса с содержанием: \"%s\"", question)));
    }
}
