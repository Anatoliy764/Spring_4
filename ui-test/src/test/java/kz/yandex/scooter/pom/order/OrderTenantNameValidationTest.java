package kz.yandex.scooter.pom.order;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.pom.home.Header;
import kz.yandex.scooter.util.WebDriverFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrderTenantNameValidationTest {

    private static final String VALID_NAME = "Джеймс";
    private static final String BLANK_NAME = " ";
    private static final String EMPTY_NAME = "";
    private static final String SHORT_NAME = "Д";
    private static final String LONG_NAME =
            "Ддддддддддддддддддддджжжжжжжжжжжжжжжжжжжжжжжеееееееееееееееееееееееееееееее..."
            + "ййййййййййййймммммммммммммммммммммммммммммммсссссссссссссссссссс";
    private static final String DIGIT_NAME = "12345";
    private static final String LATIN_NAME = "James";
    private static final String LOWERCASE_NAME = "джеймс";
    private static final String UPPERCASE_NAME = "ДЖЕЙМС";
    private static final String NAME_WITH_SPACES = " Джеймс ";
    private static final String NAME_WITH_MIDDLE_SPACE = "Дже ймс";
    private static final String SPECIAL_CHARS_NAME = "!@#$%^&*()-_=+";


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

    // region Name field tests

    @Test
    public void testNameShouldBeValid() {
        tenant.setName(VALID_NAME);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertTrue(tenant.isNameValid());
    }

    @Test
    public void testBlankNameShouldBeInvalid() {
        tenant.setName(BLANK_NAME);
        // на всякий случай инициируем валидацию на фронте кликом по форме 
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testEmptyNameShouldBeInvalid() {
        tenant.setName(EMPTY_NAME);
        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testShortNameShouldBeInvalid() {
        tenant.setName(SHORT_NAME);
        // на всякий случай инициируем валидацию на фронте кликом по форме 
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testLongNameShouldBeInvalid() {

        tenant.setName(LONG_NAME);

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    @Test
    public void testDigitNameShouldBeInvalid() {

        tenant.setName(DIGIT_NAME);

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

        tenant.setName(LATIN_NAME);

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    // Имена начинаются с большой буквы. Ожидается что имя введенное с маленькой буквы не валидно.
    // Хотя как правило системы сами переводят первую букву в верхний регистр.
    @Test
    public void testLowercaseNameShouldBeInvalid() {

        tenant.setName(LOWERCASE_NAME).getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    // Имена начинаются с большой буквы. Ожидается что имя введенное с маленькой буквы не валидно.
    // Хотя как правило системы сами переводят первую букву в верхний регистр.
    @Test
    public void testUppercaseNameShouldBeInvalid() {

        tenant.setName(UPPERCASE_NAME).getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    // Чаще всего пробелы в начале и в конце убираются автоматически, даже если не на фронте, то на бэке выполняется функция trim
    // Однако даже в бэке сохраняется имя с пробелами. Фамилию например нельзя ввести с пробелами,
    // потому считаем ошибкой если фронт допускает ввод пробелов.
    @Test
    public void testSpaceNameShouldBeInvalid() {

        tenant.setName(NAME_WITH_SPACES).getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }

    // Имена в которых есть пробел редки и чаще всего это двойные имена испаноязычных групп.
    // Ввиду отсутствия требований не считаем за ошибку если система считает имя с пробелом валидным
    @Test
    public void testMiddleSpaceNameShouldBeValid() {

        tenant.setName(NAME_WITH_MIDDLE_SPACE);

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertTrue(tenant.isNameValid());
    }

    @Test
    public void testSpecialCharsNameShouldBeInvalid() {

        tenant.setName(SPECIAL_CHARS_NAME).getWebElement().click();

        // на всякий случай инициируем валидацию на фронте кликом по форме
        tenant.getFormTitle().click();

        assertFalse(tenant.isNameValid());
    }
    // endregion

    @After
    public void tearDown() {
        driver.close();
    }
}
