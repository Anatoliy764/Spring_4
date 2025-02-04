package yandex_scooter.ui_test.pom.home;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.constants.Locator;
import yandex_scooter.ui_test.util.WebDriverFactory;
import yandex_scooter.ui_test.util.WebElementUtil;

import static org.junit.Assert.*;

public class FAQTest {

    private static WebDriver webDriver;

    @BeforeClass
    public static void init() {
        try {
            webDriver = WebDriverFactory.create();
            webDriver.get(CommonConstant.URL_YANDEX_SCOOTER);
        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate chrome webDriver. Reason: " + e.getMessage());
        }

        HomePage homePage = new HomePage(webDriver).scrollDown();
        
        try {
            WebElement faqAccordion = homePage.getWebElement().findElement(By.xpath(Locator.HomePage.XPATH_FAQ));
            assertTrue(faqAccordion.isDisplayed());
        } catch (NoSuchElementException e) {
            fail("Не найдены \"Вопросы о важном\" на главной странице");
        }
    }

    @Test
    public void testPriceAndPaymentFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_QUESTION_PRICE_AND_PAYMENT);

        assertTrue(accordionItemHeading.isDisplayed());

        String question = accordionItemHeading.getText();

        assertNotNull(question);

        assertFalse(question.isEmpty());

        assertEquals(FAQ.PRICE_AND_PAYMENT.getQuestion(), question);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_ANSWER_PRICE_AND_PAYMENT);

        assertTrue(accordionItemPanel.isDisplayed());

        String answer = accordionItemPanel.getText();

        assertNotNull(answer);

        assertFalse(answer.isEmpty());

        assertEquals(FAQ.PRICE_AND_PAYMENT.getAnswer(), answer);
    }

    @Test
    public void testRentMultipleScootersFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_QUESTION_RENT_MULTIPLE_SCOOTERS);

        assertTrue(accordionItemHeading.isDisplayed());

        String question = accordionItemHeading.getText();

        assertNotNull(question);

        assertFalse(question.isEmpty());

        assertEquals(FAQ.RENT_MULTIPLE_SCOOTERS.getQuestion(), question);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_ANSWER_RENT_MULTIPLE_SCOOTERS);

        assertTrue(accordionItemPanel.isDisplayed());

        String answer = accordionItemPanel.getText();

        assertNotNull(answer);

        assertFalse(answer.isEmpty());

        assertEquals(FAQ.RENT_MULTIPLE_SCOOTERS.getAnswer(), answer);
    }

    @Test
    public void testRentalTimeCalculationFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_QUESTION_RENTAL_TIME_CALCULATION);

        assertTrue(accordionItemHeading.isDisplayed());

        String question = accordionItemHeading.getText();

        assertNotNull(question);

        assertFalse(question.isEmpty());

        assertEquals(FAQ.RENTAL_TIME_CALCULATION.getQuestion(), question);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_ANSWER_RENTAL_TIME_CALCULATION);

        assertTrue(accordionItemPanel.isDisplayed());

        String answer = accordionItemPanel.getText();

        assertNotNull(answer);

        assertFalse(answer.isEmpty());

        assertEquals(FAQ.RENTAL_TIME_CALCULATION.getAnswer(), answer);
    }

    @Test
    public void testRentScooterForTodayFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_QUESTION_RENT_SCOOTER_FOR_TODAY);

        assertTrue(accordionItemHeading.isDisplayed());

        String question = accordionItemHeading.getText();

        assertNotNull(question);

        assertFalse(question.isEmpty());

        assertEquals(FAQ.RENT_SCOOTER_FOR_TODAY.getQuestion(), question);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_ANSWER_RENT_SCOOTER_FOR_TODAY);

        assertTrue(accordionItemPanel.isDisplayed());

        String answer = accordionItemPanel.getText();

        assertNotNull(answer);

        assertFalse(answer.isEmpty());

        assertEquals(FAQ.RENT_SCOOTER_FOR_TODAY.getAnswer(), answer);
    }

    @Test
    public void testOrderEditFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_QUESTION_ORDER_EDIT);

        assertTrue(accordionItemHeading.isDisplayed());

        String question = accordionItemHeading.getText();

        assertNotNull(question);

        assertFalse(question.isEmpty());

        assertEquals(FAQ.ORDER_EDIT.getQuestion(), question);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_ANSWER_ORDER_EDIT);

        assertTrue(accordionItemPanel.isDisplayed());

        String answer = accordionItemPanel.getText();

        assertNotNull(answer);

        assertFalse(answer.isEmpty());

        assertEquals(FAQ.ORDER_EDIT.getAnswer(), answer);
    }

    @Test
    public void testScooterWithChargerFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_QUESTION_SCOOTER_WITH_CHARGER);

        assertTrue(accordionItemHeading.isDisplayed());

        String question = accordionItemHeading.getText();

        assertNotNull(question);

        assertFalse(question.isEmpty());

        assertEquals(FAQ.SCOOTER_WITH_CHARGER.getQuestion(), question);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_ANSWER_SCOOTER_WITH_CHARGER);

        assertTrue(accordionItemPanel.isDisplayed());

        String answer = accordionItemPanel.getText();

        assertNotNull(answer);

        assertFalse(answer.isEmpty());

        assertEquals(FAQ.SCOOTER_WITH_CHARGER.getAnswer(), answer);
    }

    @Test
    public void testCancelOrderFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_QUESTION_CANCEL_ORDER);

        assertTrue(accordionItemHeading.isDisplayed());

        String question = accordionItemHeading.getText();

        assertNotNull(question);

        assertFalse(question.isEmpty());

        assertEquals(FAQ.CANCEL_ORDER.getQuestion(), question);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_ANSWER_CANCEL_ORDER);

        assertTrue(accordionItemPanel.isDisplayed());

        String answer = accordionItemPanel.getText();

        assertNotNull(answer);

        assertFalse(answer.isEmpty());

        assertEquals(FAQ.CANCEL_ORDER.getAnswer(), answer);
    }

    @Test
    public void testOrderAreaFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_QUESTION_ORDER_AREA);

        assertTrue(accordionItemHeading.isDisplayed());

        String question = accordionItemHeading.getText();

        assertNotNull(question);

        assertFalse(question.isEmpty());

        assertEquals(FAQ.ORDER_AREA.getQuestion(), question);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, Locator.HomePage.SELECTOR_FAQ_ANSWER_ORDER_AREA);

        assertTrue(accordionItemPanel.isDisplayed());

        String answer = accordionItemPanel.getText();

        assertNotNull(answer);

        assertFalse(answer.isEmpty());

        assertEquals(FAQ.ORDER_AREA.getAnswer(), answer);
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }

}
