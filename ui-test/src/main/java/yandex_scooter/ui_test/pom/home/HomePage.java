package yandex_scooter.ui_test.pom.home;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import yandex_scooter.ui_test.constants.Locator;
import yandex_scooter.ui_test.pom.PageObjectModel;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomePage extends PageObjectModel {

    public HomePage(@NonNull WebDriver webDriver) {
        super(webDriver, By.xpath(Locator.HomePage.XPATH_ROOT));
    }

    public HomePage scrollDown() {
        return scrollDown(0);
    }

    /**
     * @param x координата по оси X
     * */
    public HomePage scrollDown(int x) {
        ((JavascriptExecutor) webDriver)
                .executeScript(String.format("window.scrollTo(%d, document.body.scrollHeight)", x));

        return this;
    }
}
