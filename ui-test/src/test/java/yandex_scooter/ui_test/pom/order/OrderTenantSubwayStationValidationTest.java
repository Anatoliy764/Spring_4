package yandex_scooter.ui_test.pom.order;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import yandex_scooter.ui_test.OrderedRunner;
import yandex_scooter.ui_test.TestOrder;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.pom.home.Header;
import yandex_scooter.ui_test.util.WebDriverFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(OrderedRunner.class)
public class OrderTenantSubwayStationValidationTest {
    private static WebDriver driver;

    private static Order.Tenant tenant;

    @BeforeClass
    public static void setUp() {

        try {
            driver = WebDriverFactory.create();
            driver.get(CommonConstant.URL_YANDEX_SCOOTER);
        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate chrome driver. Reason: " + e.getMessage());
        }
        // open order tenant window
        tenant = new Header(driver).clickOrderButton().getTenant();
    }

    @After
    public void closeSubwayStationSelect() {
        tenant.getFormTitle().click();
    }

    @Test
    @TestOrder(1)
    public void testBlankSubwayStationShouldBeInvalid() {
        // ошибка валидации в случае с выпадающим списком проявляется только после нажатия на кнопку "Далее"
        try {
            tenant.clickNextButton();
        } catch (TimeoutException | NoSuchElementException ignored) {
        }

        assertFalse(tenant.isSubwayStationValid());
    }

    @Test
    @TestOrder(2)
    public void testSubwayStationShouldBeValid() {
        tenant.setSubwayStation(SubwayStation.random().getName());
        assertTrue(tenant.isSubwayStationValid());
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
