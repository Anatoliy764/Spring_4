package kz.yandex.scooter.pom.home;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.pom.PageObjectModel;
import kz.yandex.scooter.pom.order.Order;

public class Header extends PageObjectModel {

    /**
     * Заголовок главной страницы
     */
    public static final String XPATH_ROOT = "//*[@id=\"root\"]/div/div/div[1]";

    /**
     * Логотип "Самокат"
     */
    public static final String CLASS_SCOOTER_LOGO = "Header_LogoScooter__3lsAR";

    /**
     * Логотип "Яндекс"
     */
    public static final String CLASS_YANDEX_LOGO = "Header_LogoYandex__3TSOI";

    /**
     * Кнопка "Заказать"
     */
    public static final String XPATH_ORDER_BUTTON = "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]";

    /**
     * Кнопка "Статус заказа"
     */
    public static final String XPATH_ORDER_STATUS_BUTTON = "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[2]";

    /**
     * Поле для ввода идентификатора заказа
     */
    public static final String XPATH_ORDER_ID_INPUT = "//*[@id=\"root\"]/div/div/div[1]/div[3]/div/input";

    /**
     * Кнопка "Go!" запускающая поиск заказа по идентификатору и открывающая форму деталей заказа или форму,
     * сообщающую о том что заказ не найден
     */
    public static final String XPATH_ORDER_ID_SUBMIT_BUTTON = "//*[@id=\"root\"]/div/div/div[1]/div[3]/button";

    // объявлены как final т.к. видны в хидере
    private final WebElement yandexLogo;
    private final WebElement scooterLogo;
    private final WebElement orderButton;
    private final WebElement orderStatusButton;

    // становятся видны только после нажатия на кнопку "Статус заказа"
    private WebElement orderIdInput;
    private WebElement orderIdSubmitButton;

    public Header(@NonNull WebDriver webDriver) {
        super(webDriver, By.xpath(XPATH_ROOT));

        yandexLogo = webElement.findElement(By.className(CLASS_YANDEX_LOGO));
        scooterLogo = webElement.findElement(By.className(CLASS_SCOOTER_LOGO));
        orderButton = webElement.findElement(By.xpath(XPATH_ORDER_BUTTON));
        orderStatusButton = webElement.findElement(By.xpath(XPATH_ORDER_STATUS_BUTTON));
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

    public Header clickOrderStatusButton() {

        if (!orderStatusButton.isDisplayed()) {
            throw new IllegalStateException("Кнопка \"Статус заказа\" не отображена");
        }
        if (!orderStatusButton.isEnabled()) {
            throw new IllegalStateException("Кнопка \"Статус заказа\" не доступна для нажатия");
        }
        orderStatusButton.click();

        // инициализируем кнопку и поле для ввода
        orderIdInput = webDriver.findElement(By.xpath(XPATH_ORDER_ID_INPUT));
        orderIdSubmitButton = webDriver.findElement(By.xpath(XPATH_ORDER_ID_SUBMIT_BUTTON));

        // ждем отображения поля для ввода идентификатора заказа и кнопки Go!
        new WebDriverWait(webDriver, CommonConstant.TIME_OUT)
                .until(driver -> orderIdInput.isDisplayed() && orderIdSubmitButton.isDisplayed());

        return this;
    }

    public Header setOrderId(String orderId) {
        validateOrderIdInputState();
        orderIdInput.clear();
        orderIdInput.sendKeys(orderId);
        return this;
    }

    public void validateOrderIdInput() {
        validateOrderIdInputState();
        // orderIdInput.getText() не возвращает значение, поэтому извлекаем из атрибута value
        String orderId = orderIdInput.getDomAttribute("value");
        if (orderId.isEmpty()) {
            throw new IllegalStateException("Поле идентификатора заказа не инициализировано. Введите идентификатор заказа.");
        }
    }

    public void validateOrderIdInputState() {
        if (orderIdInput == null) {
            throw new IllegalStateException("Поле для ввода идентификатора заказа не инициализировано. Сначала нажмите на кнопку \"Статус заказа\"");
        }
        if (!orderIdInput.isDisplayed()) {
            throw new IllegalStateException("Поле для ввода идентификатора заказа не отображено");
        }
        if (!orderIdInput.isEnabled()) {
            throw new IllegalStateException("Поле идентификатора заказа не доступно для ввода");
        }
    }

    public Header clickOrderIdSubmitButton() {
        validateOrderIdInput();

        if (!orderIdSubmitButton.isDisplayed()) {
            throw new IllegalStateException("Кнопка \"Go!\" не отображена");
        }
        if (!orderIdSubmitButton.isEnabled()) {
            throw new IllegalStateException("Кнопка \"Go!\" не доступна для нажатия");
        }
        orderIdSubmitButton.click();
        return this;
    }

    public void clickYandexLogo() {
        if (!yandexLogo.isDisplayed()) {
            throw new IllegalStateException("Логотип \"Яндекс\" не отображен");
        }
        yandexLogo.click();
    }

    public void clickScooterLogo() {
        if (!scooterLogo.isDisplayed()) {
            throw new IllegalStateException("Логотип \"Самокат\" не отображен");
        }
        scooterLogo.click();
    }
}
