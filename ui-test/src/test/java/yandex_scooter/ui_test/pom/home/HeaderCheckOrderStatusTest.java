package yandex_scooter.ui_test.pom.home;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.constants.Locator;
import yandex_scooter.ui_test.util.WebDriverFactory;

import static org.junit.Assert.*;

public class HeaderCheckOrderStatusTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        try {
            webDriver = WebDriverFactory.create();
            webDriver.get(CommonConstant.URL_YANDEX_SCOOTER);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось инициализировать WebDriver. Причина: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void testCheckOrderStatus() {
        try {
            new Header(webDriver).clickOrderStatusButton().setOrderId("1").clickOrderIdSubmitButton();
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        WebElement orderNotFoundImage = null;
        try {
            orderNotFoundImage = webDriver.findElement(By.ByCssSelector.cssSelector(Locator.OrderTracking.CSS_SELECTOR_ORDER_NOT_FOUND_IMAGE));
        } catch (NoSuchElementException e) {
            fail("Не найден ожидаемый компонент с картинкой с текстом \"Такого заказа нет\" " + e.getMessage());
        }

        assertNotNull(orderNotFoundImage);
        String imageSource = orderNotFoundImage.getDomAttribute("src");
        assertNotNull(imageSource);
        assertEquals("/assets/not-found.png", imageSource.trim());

        String alt = orderNotFoundImage.getDomAttribute("alt");
        assertNotNull(alt);
        assertEquals("Not found", alt.trim());
    }

}
