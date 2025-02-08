package kz.yandex.scooter.pom.order;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import kz.yandex.scooter.OrderedRunner;
import kz.yandex.scooter.TestOrder;
import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.pom.home.Header;
import kz.yandex.scooter.util.WebDriverFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(OrderedRunner.class)
public class OrderTenantSubwayStationValidationTest {
    private WebDriver driver;

    private Order.Tenant tenant;

    @Before
    public void setUp() {

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

    @After
    public void tearDown() {
        driver.close();
    }
}
