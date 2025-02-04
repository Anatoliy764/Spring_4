package yandex_scooter.ui_test.pom.home;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.constants.Locator;
import yandex_scooter.ui_test.pom.PageObjectModel;
import yandex_scooter.ui_test.pom.order.Order;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Header extends PageObjectModel {

    // объявлены как final т.к. видны в хидере
    final WebElement yandexLogo;
    final WebElement scooterLogo;
    final WebElement orderButton;
    final WebElement orderStatusButton;

    // становятся видны только после нажатия на кнопку "Статус заказа"
    WebElement orderIdInput;
    WebElement orderIdSubmitButton;

    public Header(@NonNull WebDriver webDriver) {
        super(webDriver, By.xpath(Locator.Header.XPATH_ROOT));

        yandexLogo = webElement.findElement(By.className(Locator.Header.CLASS_YANDEX_LOGO));
        scooterLogo = webElement.findElement(By.className(Locator.Header.CLASS_SCOOTER_LOGO));
        orderButton = webElement.findElement(By.xpath(Locator.Header.XPATH_ORDER_BUTTON));
        orderStatusButton = webElement.findElement(By.xpath(Locator.Header.XPATH_ORDER_STATUS_BUTTON));
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
        orderIdInput = webDriver.findElement(By.xpath(Locator.Header.XPATH_ORDER_ID_INPUT));
        orderIdSubmitButton = webDriver.findElement(By.xpath(Locator.Header.XPATH_ORDER_ID_SUBMIT_BUTTON));

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
        if(!yandexLogo.isDisplayed()) {
            throw new IllegalStateException("Логотип \"Яндекс\" не отображен");
        }
        yandexLogo.click();
    }

    public void clickScooterLogo() {
        if(!scooterLogo.isDisplayed()) {
            throw new IllegalStateException("Логотип \"Самокат\" не отображен");
        }
        scooterLogo.click();
    }
}
