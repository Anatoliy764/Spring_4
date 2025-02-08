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

public class OrderTenantNameValidationTest {
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
    public void testNameShouldBeValid() {
        tenant.setName("Джеймс");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertTrue(tenant.isNameValid());
    }

    @Test
    public void testBlankNameShouldBeInvalid() {
        tenant.setName(" ");
        // на всякий случай инициируем валидацию на фронте кликом по форме 
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testEmptyNameShouldBeInvalid() {
        tenant.setName("");
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testShortNameShouldBeInvalid() {
        tenant.setName("Д");
        // на всякий случай инициируем валидацию на фронте кликом по форме 
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testLongNameShouldBeInvalid() {

        tenant.setName("Ддддддддддддддддддддджжжжжжжжжжжжжжжжжжжжжжжеееееееееееееееееееееееееееееееейййййййййййййййййййййййййййййййййймммммммммммммммммммммммммммммсссссссссссссссссссссс");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testDigitNameShouldBeInvalid() {

        tenant.setName("12345");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    /*
     * Т.к. нет конкретных требований, не понятно почему форма не принимает значения на латинице.
     * Вполне вероятно что иностранец захочет взять самокат и будет вводить на латинице.
     * Даже учитывая то, что интерфейс на русском, некоторые включают в браузере автоматический перевод страницы, поэтому такой кейс вполне себе вероятен.
     * Однако система не принимает латиницу, поэтому фактически тест подстроен под поведение системы, а не наоборот.
     * */
    @Test
    public void testLatinNameShouldBeInvalid() {

        tenant.setName("James");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    // Имена начинаются с большой буквы. Ожидается что имя введенное с маленькой буквы не валидно.
    // Хотя как правило системы сами переводят первую букву в верхний регистр.
    @Test
    public void testLowercaseNameShouldBeInvalid() {

        tenant.setName("джеймс").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    // Имена начинаются с большой буквы. Ожидается что имя введенное с маленькой буквы не валидно.
    // Хотя как правило системы сами переводят первую букву в верхний регистр.
    @Test
    public void testUppercaseNameShouldBeInvalid() {

        tenant.setName("ДЖЕЙМС").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    // Чаще всего пробелы в начале и в конце убираются автоматически, даже если не на фронте, то на бэке выполняется функция trim
    // Однако даже в бэке сохраняется имя с пробелами. Фамилию например нельзя ввести с пробелами,
    // потому считаем ошибкой если фронт допускает ввод пробелов.
    @Test
    public void testSpaceNameShouldBeInvalid() {

        tenant.setName(" Джеймс ").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    // Имена в которых есть пробел редки и чаще всего это двойные имена испаноязычных групп.
    // Ввиду отсутствия требований не считаем за ошибку если система считает имя с пробелом валидным
    @Test
    public void testMiddleSpaceNameShouldBeValid() {

        tenant.setName("Дже ймс");

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertTrue(tenant.isNameValid());
    }

    @Test
    public void testSpecialCharsNameShouldBeInvalid() {

        tenant.setName("!@#$%^&*()-_=+").getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }
    // endregion

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
