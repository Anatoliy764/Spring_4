package yandex_scooter.ui_test.pom.order;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.pom.home.Header;
import yandex_scooter.ui_test.util.WebDriverFactory;

import static org.junit.Assert.*;

public class OrderTenantPhoneNumberValidationTest {

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

    // region Phone number tests
    @Test
    public void testEmptyPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber("");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testBlankPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber(" ");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testLetterPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber("ffff");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testShortPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber("9999");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testLongPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber("999999999999999");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testSpecialCharsPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber("!@#$%^&*()-+");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testPlusPrefixPhoneNumberShouldBeValid() {
        tenant.setPhoneNumber("+996706696409");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertTrue(tenant.isPhoneNumberValid());
    }

    @Test
    public void testPhoneNumberShouldBeValid() {
        tenant.setPhoneNumber("996706696409");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertTrue(tenant.isPhoneNumberValid());
    }
    // endregion

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
