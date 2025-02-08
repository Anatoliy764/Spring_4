package kz.yandex.scooter.pom.home;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.util.WebDriverFactory;
import kz.yandex.scooter.util.WebElementUtil;

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
            WebElement faqAccordion = homePage.getWebElement().findElement(By.xpath(HomePage.XPATH_FAQ));
            assertTrue(faqAccordion.isDisplayed());
        } catch (NoSuchElementException e) {
            fail("Не найдены \"Вопросы о важном\" на главной странице");
        }
    }

    @Test
    public void testPriceAndPaymentFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_QUESTION_PRICE_AND_PAYMENT);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_ANSWER_PRICE_AND_PAYMENT);

        String answer = accordionItemPanel.getText();

        assertEquals(FAQ.PRICE_AND_PAYMENT.getAnswer(), answer);
    }

    @Test
    public void testRentMultipleScootersFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_QUESTION_RENT_MULTIPLE_SCOOTERS);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_ANSWER_RENT_MULTIPLE_SCOOTERS);

        String answer = accordionItemPanel.getText();

        assertEquals(FAQ.RENT_MULTIPLE_SCOOTERS.getAnswer(), answer);
    }

    @Test
    public void testRentalTimeCalculationFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_QUESTION_RENTAL_TIME_CALCULATION);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_ANSWER_RENTAL_TIME_CALCULATION);

        String answer = accordionItemPanel.getText();

        assertEquals(FAQ.RENTAL_TIME_CALCULATION.getAnswer(), answer);
    }

    @Test
    public void testRentScooterForTodayFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_QUESTION_RENT_SCOOTER_FOR_TODAY);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_ANSWER_RENT_SCOOTER_FOR_TODAY);

        String answer = accordionItemPanel.getText();

        assertEquals(FAQ.RENT_SCOOTER_FOR_TODAY.getAnswer(), answer);
    }

    @Test
    public void testOrderEditFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_QUESTION_ORDER_EDIT);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_ANSWER_ORDER_EDIT);

        String answer = accordionItemPanel.getText();

        assertEquals(FAQ.ORDER_EDIT.getAnswer(), answer);
    }

    @Test
    public void testScooterWithChargerFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_QUESTION_SCOOTER_WITH_CHARGER);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_ANSWER_SCOOTER_WITH_CHARGER);

        String answer = accordionItemPanel.getText();

        assertEquals(FAQ.SCOOTER_WITH_CHARGER.getAnswer(), answer);
    }

    @Test
    public void testCancelOrderFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_QUESTION_CANCEL_ORDER);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_ANSWER_CANCEL_ORDER);

        String answer = accordionItemPanel.getText();

        assertEquals(FAQ.CANCEL_ORDER.getAnswer(), answer);
    }

    @Test
    public void testOrderAreaFAQ() {
        WebElement accordionItemHeading = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_QUESTION_ORDER_AREA);

        accordionItemHeading.click();

        WebElement accordionItemPanel = WebElementUtil.deepSearch(webDriver, HomePage.SELECTOR_FAQ_ANSWER_ORDER_AREA);

        String answer = accordionItemPanel.getText();

        assertEquals(FAQ.ORDER_AREA.getAnswer(), answer);
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }

}
