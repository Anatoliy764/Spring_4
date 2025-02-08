package kz.yandex.scooter.pom.home;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import kz.yandex.scooter.pom.PageObjectModel;
import kz.yandex.scooter.pom.order.Order;

public class HomePage extends PageObjectModel {

    /**
     * Главная страница
     */
    public static final String XPATH_ROOT = "//*[@id=\"root\"]/div/div";

    /**
     * Кнопка "Заказать" на странице
     */
    public static final String XPATH_ORDER_BTN = "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button";

    /**
     * Текстовый заголовок на главной странице "Самокат на пару дней"
     */
    public static final String XPATH_TITLE = "//*[@id=\"root\"]/div/div/div[2]/div[4]";

    public static final String XPATH_FAQ = "//*[@id=\"root\"]/div/div/div[5]/div[2]/div";

    public static final String SELECTOR_FAQ_QUESTION_PRICE_AND_PAYMENT = "#accordion__heading-0";
    public static final String SELECTOR_FAQ_ANSWER_PRICE_AND_PAYMENT = "#accordion__panel-0 > p";

    public static final String SELECTOR_FAQ_QUESTION_RENT_MULTIPLE_SCOOTERS = "#accordion__heading-1";
    public static final String SELECTOR_FAQ_ANSWER_RENT_MULTIPLE_SCOOTERS = "#accordion__panel-1 > p";

    public static final String SELECTOR_FAQ_QUESTION_RENTAL_TIME_CALCULATION = "#accordion__heading-2";
    public static final String SELECTOR_FAQ_ANSWER_RENTAL_TIME_CALCULATION = "#accordion__panel-2 > p";

    public static final String SELECTOR_FAQ_QUESTION_RENT_SCOOTER_FOR_TODAY = "#accordion__heading-3";
    public static final String SELECTOR_FAQ_ANSWER_RENT_SCOOTER_FOR_TODAY = "#accordion__panel-3 > p";

    public static final String SELECTOR_FAQ_QUESTION_ORDER_EDIT = "#accordion__heading-4";
    public static final String SELECTOR_FAQ_ANSWER_ORDER_EDIT = "#accordion__panel-4 > p";

    public static final String SELECTOR_FAQ_QUESTION_SCOOTER_WITH_CHARGER = "#accordion__heading-5";
    public static final String SELECTOR_FAQ_ANSWER_SCOOTER_WITH_CHARGER = "#accordion__panel-5 > p";

    public static final String SELECTOR_FAQ_QUESTION_CANCEL_ORDER = "#accordion__heading-6";
    public static final String SELECTOR_FAQ_ANSWER_CANCEL_ORDER = "#accordion__panel-6 > p";

    public static final String SELECTOR_FAQ_QUESTION_ORDER_AREA = "#accordion__heading-7";
    public static final String SELECTOR_FAQ_ANSWER_ORDER_AREA = "#accordion__panel-7 > p";

    private final WebElement orderButton;

    public HomePage(@NonNull WebDriver webDriver) {
        super(webDriver, By.xpath(XPATH_ROOT));

        orderButton = webDriver.findElement(By.xpath(XPATH_ORDER_BTN));
    }

    public HomePage scrollDown() {
        return scrollDown(0);
    }

    /**
     * @param x координата по оси X
     */
    public HomePage scrollDown(int x) {
        ((JavascriptExecutor) webDriver)
                .executeScript(String.format("window.scrollTo(%d, document.body.scrollHeight)", x));

        return this;
    }

    public Header getHeader() {
        return new Header(webDriver);
    }

    public Order clickOrderButton() {
        if (!orderButton.isDisplayed()) {
            throw new IllegalStateException("Кнопка \"Заказать\" не отображена");
        }
        if (!orderButton.isEnabled()) {
            throw new IllegalStateException("Кнопка \"Заказать\" не доступна для нажатия");
        }
        orderButton.click();

        return new Order(webDriver);
    }
}
