package yandex_scooter.ui_test.pom.order;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.pom.home.Header;
import yandex_scooter.ui_test.util.WebDriverFactory;

import static org.junit.Assert.*;

public class OrderValidationTenantNameTest {
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

    // region Name field tests

    @Test
    public void testOrderTenantNameShouldBeValid() {
        tenant.setName("Анатолий");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getWebElement().click();

        assertTrue(tenant.isNameValid());
    }

    @Test
    public void testOrderTenantBlankNameShouldBeInvalid() {
        tenant.setName(" ");
        // на всякий случай инициируем валидацию на фронте кликом по форме 
        tenant.getWebElement().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testOrderTenantShortNameShouldBeInvalid() {
        tenant.setName("А");
        // на всякий случай инициируем валидацию на фронте кликом по форме 
        tenant.getWebElement().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testOrderTenantLongNameShouldBeInvalid() {

        tenant.setName("Вольфешлегельштайнхаузенбергердорф");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getWebElement().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testOrderTenantDigitNameShouldBeInvalid() {

        tenant.setName("123");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getWebElement().click();

        assertFalse(tenant.isNameValid());
    }

    /*
    * Т.к. нет конкретных требований, не понятно почему форма не принимает ФИО на латинице.
    * Вполне вероятно что иностранец захочет взять самокат и будет вводить на латинице.
    * Даже учитывая то, что интерфейс на русском, некоторые включают в браузере автоматический перевод страницы, поэтому такой кейс вполне себе вероятен.
    * Однако система не принимает латиницу, поэтому фактически тест подстроен под поведение системы, а не наоборот.
    * */
    @Test
    public void testOrderTenantLatinNameShouldBeInvalid() {

        tenant.setName("Anatoly");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getWebElement().click();

        assertFalse(tenant.isNameValid());
    }

    // Имена начинаются с большой буквы. Ожидается что имя введенное с маленькой буквы не валидно.
    // Хотя как правило системы сами переводят первую букву в верхний регистр.
    @Test
    public void testOrderTenantLowercaseNameValidation() {

        tenant.setName("анатолий").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getWebElement().click();

        assertFalse(tenant.isNameValid());
    }

    // Чаще всего пробелы в начале и в конце убираются автоматически, даже если не на фронте, то на бэке выполняется функция trim
    // Однако даже в бэке сохраняется имя с пробелами. Фамилию например нельзя ввести с пробелами,
    // потому считаем ошибкой если фронт допускает ввод пробелов.
    @Test
    public void testOrderTenantSpaceNameValidation() {

        tenant.setName(" Анатолий").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getWebElement().click();

        assertFalse(tenant.isNameValid());
    }

    // Имена в которых есть пробел редки и чаще всего это двойные имена испаноязычных групп.
    // Ввиду отсутствия требований не считаем за ошибку если система считает имя с пробелом валидным
    @Test
    public void testOrderTenantMiddleSpaceNameValidation() {

        tenant.setName("Ана толий");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getWebElement().click();

        assertTrue(tenant.isNameValid());
    }

    @Test
    public void testOrderTenantSpecialCharsNameValidation() {

        tenant.setName("@н@тол|/|й").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getWebElement().click();

        assertFalse(tenant.isNameValid());
    }
    // endregion

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
