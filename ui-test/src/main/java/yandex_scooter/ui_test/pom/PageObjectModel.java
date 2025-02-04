package yandex_scooter.ui_test.pom;

import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import yandex_scooter.ui_test.constants.CommonConstant;

@Getter
public abstract class PageObjectModel {

    @NonNull
    protected final WebDriver webDriver;

    @NonNull
    protected final By locator;

    protected final WebElement webElement;

    public PageObjectModel(@NonNull WebDriver webDriver,
                           @NonNull By locator) {
        this.webDriver = webDriver;
        this.locator = locator;
        this.webElement = webDriver.findElement(locator);

        // ждем пока элемент отобразится во избежание ошибок
        new WebDriverWait(webDriver, CommonConstant.TIME_OUT)
                .until(d ->isDisplayed());
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public boolean isEnabled() {
        return webElement.isEnabled();
    }
}
