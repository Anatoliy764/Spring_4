package kz.yandex.scooter.pom.order;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.pom.home.Header;
import kz.yandex.scooter.util.WebDriverFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrderTenantPhoneNumberValidationTest {

    private static final String EMPTY_PHONE_NUMBER = "";
    private static final String BLANK_PHONE_NUMBER = " ";
    private static final String LETTER_PHONE_NUMBER = "ffff";
    private static final String SHORT_PHONE_NUMBER = "9999";
    private static final String LONG_PHONE_NUMBER = "999999999999999";
    private static final String SPECIAL_CHARS_PHONE_NUMBER = "!@#$%^&*()-+";
    private static final String VALID_PLUS_PREFIX_PHONE_NUMBER = "+996706696409";
    private static final String VALID_PHONE_NUMBER = "996706696409";


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

    // region Phone number tests
    @Test
    public void testEmptyPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber(EMPTY_PHONE_NUMBER);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testBlankPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber(BLANK_PHONE_NUMBER);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testLetterPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber(LETTER_PHONE_NUMBER);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testShortPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber(SHORT_PHONE_NUMBER);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testLongPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber(LONG_PHONE_NUMBER);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testSpecialCharsPhoneNumberShouldBeInvalid() {
        tenant.setPhoneNumber(SPECIAL_CHARS_PHONE_NUMBER);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertFalse(tenant.isPhoneNumberValid());
    }

    @Test
    public void testPlusPrefixPhoneNumberShouldBeValid() {
        tenant.setPhoneNumber(VALID_PLUS_PREFIX_PHONE_NUMBER);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertTrue(tenant.isPhoneNumberValid());
    }

    @Test
    public void testPhoneNumberShouldBeValid() {
        tenant.setPhoneNumber(VALID_PHONE_NUMBER);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();
        assertTrue(tenant.isPhoneNumberValid());
    }
    // endregion

    @After
    public void tearDown() {
        driver.close();
    }
}
