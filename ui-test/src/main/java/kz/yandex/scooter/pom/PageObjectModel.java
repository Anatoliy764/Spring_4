package kz.yandex.scooter.pom;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import kz.yandex.scooter.constants.CommonConstant;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .until(d -> isDisplayed());
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    public PageObjectModel clear() {
        validateState();
        webElement.clear();
        new WebDriverWait(webDriver, CommonConstant.TIME_OUT).until(d -> getText().isEmpty());
        return this;
    }

    public PageObjectModel click() {
        validateState();
        webElement.click();
        return this;
    }

    public String getAttribute(String attribute) {
        return webElement.getDomAttribute(attribute);
    }

    public String getValueAttribute() {
        return getAttribute("value");
    }

    public PageObjectModel setValueAttribute(String value) {
        return setAttribute(webElement, "value", value);
    }

    public Collection<String> getCssClasses() {
        String cssClasses = getAttribute("class");
        if (cssClasses == null || cssClasses.isEmpty()) {
            return Collections.emptyList();
        }
        return Stream.of(cssClasses.split("\\s+")).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public boolean hasCssClass(String cssClass) {
        return getCssClasses().contains(cssClass);
    }

    public boolean hasAtLeastOneOfCssClasses(Collection<String> cssClasses) {
        return cssClasses.stream().anyMatch(this::hasCssClass);
    }

    public PageObjectModel setAttribute(WebElement webElement, String attributeName, String value) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", webElement, attributeName, value);
        return this;
    }

    public String getText() {
        return webElement.getText();
    }

    public PageObjectModel sendKeys(String keys) {
        clear();
        webElement.sendKeys(keys);
        new WebDriverWait(webDriver, CommonConstant.TIME_OUT).until(d -> Objects.equals(getText(), keys));
        return this;
    }

    public void validateState() {
        if (!isDisplayed()) {
            throw new IllegalStateException("элемент не отображен " + locator);
        }
        if (!isEnabled()) {
            throw new IllegalStateException("элемент не включен " + locator);
        }
    }

    public WebElement findElement(By locator) {
        return webElement.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return webElement.findElements(locator);
    }

    public static PageObjectModel wrap(WebDriver driver, By locator) {
        return new PageObjectModel(driver, locator) {
            @Override
            public WebElement getWebElement() {
                return super.webElement;
            }
        };
    }

    public @NonNull WebDriver getWebDriver() {
        return this.webDriver;
    }

    public @NonNull By getLocator() {
        return this.locator;
    }

    public WebElement getWebElement() {
        return this.webElement;
    }
}
