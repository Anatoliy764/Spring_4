package yandex_scooter.ui_test.pom.order;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.pom.home.Header;
import yandex_scooter.ui_test.util.WebDriverFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrderTenantDeliveryAddressValidationTest {
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
        tenant.setScooterDeliveryAddress("Романов переулок, 7, Москва, 125009");
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
        tenant.setScooterDeliveryAddress("Бейкер-стрит, 221B, Лондон");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testBlankScooterDeliveryAddressShouldBeInvalid() {
        tenant.setScooterDeliveryAddress(" ");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testEmptyScooterDeliveryAddressShouldBeInvalid() {
        tenant.setScooterDeliveryAddress("");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testShortScooterDeliveryAddressShouldBeInvalid() {
        tenant.setScooterDeliveryAddress("Б");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testLongScooterDeliveryAddressShouldBeInvalid() {

        tenant.setScooterDeliveryAddress("Бейкер-стрит, 221B, Лондоннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннннн");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testDigitsOnlyScooterDeliveryAddressShouldBeInvalid() {

        tenant.setScooterDeliveryAddress("12345");

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

        tenant.setScooterDeliveryAddress("Romanov lane, 7, Moscow, 125009");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @Test
    public void testSpecialCharsScooterDeliveryAddressShouldBeInvalid() {

        tenant.setScooterDeliveryAddress("!@#$%^&*()-_=+").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isScooterDeliveryAddressValid());
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
