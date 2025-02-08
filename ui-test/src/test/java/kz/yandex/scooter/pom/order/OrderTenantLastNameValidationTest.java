package kz.yandex.scooter.pom.order;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.pom.home.Header;
import kz.yandex.scooter.util.WebDriverFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrderTenantLastNameValidationTest {
    private static final String VALID_LAST_NAME = "Гослинг";
    private static final String BLANK_LAST_NAME = " ";
    private static final String EMPTY_LAST_NAME = "";
    private static final String SHORT_LAST_NAME = "Г";
    private static final String LONG_INVALID_LAST_NAME = "Вольфешлегельштайнхаузенбергердорффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффффф";
    private static final String DIGIT_LAST_NAME = "12345";
    private static final String LATIN_LAST_NAME = "Gosling";
    private static final String LOWERCASE_LAST_NAME = "гослинг";
    private static final String UPPERCASE_LAST_NAME = "ГОСЛИНГ";
    private static final String SPACE_LAST_NAME = " Гослинг ";
    private static final String MIDDLE_SPACE_LAST_NAME = "Гос линг";
    private static final String SPECIAL_CHARS_LAST_NAME = "!@#$%^&*()-_=+";

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

    @Test
    public void testLastNameShouldBeValid() {
        tenant.setLastName(VALID_LAST_NAME);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertTrue(tenant.isLastNameValid());
    }

    @Test
    public void testBlankLastNameShouldBeInvalid() {
        tenant.setLastName(BLANK_LAST_NAME);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @Test
    public void testEmptyLastNameShouldBeInvalid() {
        tenant.setLastName(EMPTY_LAST_NAME);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @Test
    public void testShortLastNameShouldBeInvalid() {
        tenant.setLastName(SHORT_LAST_NAME);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @Test
    public void testLongLastNameShouldBeInvalid() {
        tenant.setLastName(LONG_INVALID_LAST_NAME);

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @Test
    public void testDigitLastNameShouldBeInvalid() {
        tenant.setLastName(DIGIT_LAST_NAME);

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
        tenant.setLastName(LATIN_LAST_NAME);

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    // Фамилии начинаются с большой буквы. Ожидается что имя введенное с маленькой буквы не валидно.
    // Хотя как правило системы сами переводят первую букву в верхний регистр.
    @Test
    public void testLowercaseLastNameShouldBeInvalid() {
        tenant.setLastName(LOWERCASE_LAST_NAME).getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @Test
    public void testUppercaseLastNameShouldBeInvalid() {
        tenant.setLastName(UPPERCASE_LAST_NAME).getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    // Чаще всего пробелы в начале и в конце убираются автоматически, даже если не на фронте, то на бэке выполняется функция trim
    // Однако даже в бэке сохраняется имя с пробелами. Фамилию например нельзя ввести с пробелами,
    // потому считаем ошибкой если фронт допускает ввод пробелов.
    @Test
    public void testSpaceLastNameShouldBeInvalid() {
        tenant.setLastName(SPACE_LAST_NAME).getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    // Фамилии в которых есть пробел встречаются, например "Ван Гог"
    @Test
    public void testMiddleSpaceLastNameShouldBeValid() {
        tenant.setLastName(MIDDLE_SPACE_LAST_NAME);

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertTrue(tenant.isLastNameValid());
    }

    @Test
    public void testSpecialCharsLastNameShouldBeInvalid() {
        tenant.setLastName(SPECIAL_CHARS_LAST_NAME).getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isLastNameValid());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
