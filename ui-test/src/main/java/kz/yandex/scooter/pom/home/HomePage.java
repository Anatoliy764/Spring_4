package kz.yandex.scooter.pom.home;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import kz.yandex.scooter.constants.Locator;
import kz.yandex.scooter.pom.PageObjectModel;
import kz.yandex.scooter.pom.order.Order;

public class HomePage extends PageObjectModel {

    private final WebElement orderButton;

    public HomePage(@NonNull WebDriver webDriver) {
        super(webDriver, By.xpath(Locator.HomePage.XPATH_ROOT));

        orderButton = webDriver.findElement(By.xpath(Locator.HomePage.XPATH_ORDER_BTN));
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
