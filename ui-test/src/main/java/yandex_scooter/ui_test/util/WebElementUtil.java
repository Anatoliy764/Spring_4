package yandex_scooter.ui_test.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class WebElementUtil {

    private WebElementUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static WebElement deepSearch(WebDriver driver, String cssSelector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "function deepSearch(el, selector) {" +
                        "  if (el.matches(selector)) return el;" +
                        "  for (let child of el.children) {" +
                        "    let found = deepSearch(child, selector);" +
                        "    if (found) return found;" +
                        "  }" +
                        "  return null;" +
                        "}" +
                        "return deepSearch(document.body, arguments[0]);";
        return (WebElement) js.executeScript(script, cssSelector);
    }

}
