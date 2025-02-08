package kz.yandex.scooter.pom.order;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.pom.home.Header;
import kz.yandex.scooter.util.WebDriverFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrderTenantLastNameValidationTest {
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

    @Test
    public void testLastNameShouldBeValid() {
        tenant.setLastName("Гослинг");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertTrue(tenant.isLastNameValid());
    }

    @Test
    public void testBlankLastNameShouldBeInvalid() {
        tenant.setLastName(" ");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @Test
    public void testEmptyLastNameShouldBeInvalid() {
        tenant.setLastName("");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @Test
    public void testShortLastNameShouldBeInvalid() {
        tenant.setLastName("Г");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @Test
    public void testLongLastNameShouldBeInvalid() {

        tenant.setLastName("Вольфешлегельштайнхаузенбергердорффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффф");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @Test
    public void testDigitLastNameShouldBeInvalid() {

        tenant.setLastName("12345");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    /*
     * Т.к. нет конкретных требований, не понятно почему форма не принимает ФИО на латинице.
     * Вполне вероятно что иностранец захочет взять самокат и будет вводить на латинице.
     * Даже учитывая то, что интерфейс на русском, некоторые включают в браузере автоматический перевод страницы, поэтому такой кейс вполне себе вероятен.
     * Однако система не принимает латиницу, поэтому фактически тест подстроен под поведение системы, а не наоборот.
     * */
    @Test
    public void testLatinLastNameShouldBeInvalid() {

        tenant.setLastName("Gosling");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    // Фамилии начинаются с большой буквы. Ожидается что имя введенное с маленькой буквы не валидно.
    // Хотя как правило системы сами переводят первую букву в верхний регистр.
    @Test
    public void testLowercaseLastNameShouldBeInvalid() {

        tenant.setLastName("гослинг").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @Test
    public void testUppercaseLastNameShouldBeInvalid() {

        tenant.setLastName("ГОСЛИНГ").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    // Чаще всего пробелы в начале и в конце убираются автоматически, даже если не на фронте, то на бэке выполняется функция trim
    // Однако даже в бэке сохраняется имя с пробелами. Фамилию например нельзя ввести с пробелами,
    // потому считаем ошибкой если фронт допускает ввод пробелов.
    @Test
    public void testSpaceLastNameShouldBeInvalid() {

        tenant.setLastName(" Гослинг ").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    // Фамилии в которых есть пробел встречаются, например "Ван Гог"
    @Test
    public void testMiddleSpaceLastNameShouldBeValid() {

        tenant.setLastName("Гос линг");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertTrue(tenant.isLastNameValid());
    }

    @Test
    public void testSpecialCharsLastNameShouldBeInvalid() {

        tenant.setLastName("!@#$%^&*()-_=+").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
