package kz.yandex.scooter.pom.home;

import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.util.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertThrows;

public class HeaderCheckOrderStatusTest {

    private static final String UNEXISTING_ORDER_ID = "1";

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
        assertThrows(IllegalArgumentException.class, () -> new Header(webDriver).clickOrderStatusButton().setOrderId(UNEXISTING_ORDER_ID).clickOrderIdSubmitButton());
    }

}
