package kz.yandex.scooter.pom.order;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.pom.home.Header;
import kz.yandex.scooter.util.WebDriverFactory;

import static org.junit.Assert.*;

public class OrderTenantDeliveryAddressValidationTest {

    private static final String VALID_MOSCOW_ADDRESS = "Романов переулок, 7, Москва, 125009";
    private static final String INVALID_FOREIGN_ADDRESS = "Бейкер-стрит, 221B, Лондон";
    private static final String BLANK_ADDRESS = " ";
    private static final String EMPTY_ADDRESS = "";
    private static final String SHORT_ADDRESS = "Б";
    private static final String LONG_ADDRESS = "Бейкер-стрит, 221B, Лондонннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннн";
    private static final String DIGITS_ONLY_ADDRESS = "12345";
    private static final String LATIN_ADDRESS = "Romanov lane, 7, Moscow, 125009";
    private static final String SPECIAL_CHARS_ADDRESS = "!@#$%^&*()-_=+";

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

    // Существующий адрес в пределах московской области считается валидным
    @Test
    public void testScooterDeliveryAddressShouldBeValid() {
        tenant.setScooterDeliveryAddress(VALID_MOSCOW_ADDRESS);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertTrue(tenant.isScooterDeliveryAddressValid());
    }

    // Существующий адрес за пределами России считается не валидным,
    // т.к. в FAQ "Я живу за МКАДом, привезёте?" в ответе говорится лишь про Москву и московскую область.
    // Сервис может работать и в др. городах России и теоретически даже за границей,
    // но т.к. нет требований и в системе нет географической проверки адреса,
    // считаем что все что за переделами России не валидно.
    @Test
    public void testScooterDeliveryAddressShouldBeInValid() {
        tenant.setScooterDeliveryAddress(INVALID_FOREIGN_ADDRESS);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testBlankScooterDeliveryAddressShouldBeInvalid() {
        tenant.setScooterDeliveryAddress(BLANK_ADDRESS);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testEmptyScooterDeliveryAddressShouldBeInvalid() {
        tenant.setScooterDeliveryAddress(EMPTY_ADDRESS);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testShortScooterDeliveryAddressShouldBeInvalid() {
        tenant.setScooterDeliveryAddress(SHORT_ADDRESS);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testLongScooterDeliveryAddressShouldBeInvalid() {

        tenant.setScooterDeliveryAddress(LONG_ADDRESS);

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testDigitsOnlyScooterDeliveryAddressShouldBeInvalid() {

        tenant.setScooterDeliveryAddress(DIGITS_ONLY_ADDRESS);

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    /*
     * Т.к. нет конкретных требований, не понятно почему форма не принимает значения на латинице.
     * Вполне вероятно что иностранец захочет взять самокат и будет вводить на латинице.
     * Даже учитывая то, что интерфейс на русском, некоторые включают в браузере автоматический перевод страницы, поэтому такой кейс вполне себе вероятен.
     * Однако система не принимает латиницу, поэтому фактически тест подстроен под поведение системы, а не наоборот.
     * */
    @Test
    public void testLatinScooterDeliveryAddressShouldBeInvalid() {

        tenant.setScooterDeliveryAddress(LATIN_ADDRESS);

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testSpecialCharsScooterDeliveryAddressShouldBeInvalid() {

        tenant.setScooterDeliveryAddress(SPECIAL_CHARS_ADDRESS).getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
