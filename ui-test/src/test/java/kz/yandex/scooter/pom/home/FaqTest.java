package kz.yandex.scooter.pom.home;

import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.util.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FaqTest {

    private WebDriver webDriver;
    private List<HomePage.FAQ> questions;

    @Before
    public void init() {
        try {
            webDriver = WebDriverFactory.create();
            webDriver.get(CommonConstant.URL_YANDEX_SCOOTER);
        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate chrome webDriver. Reason: " + e.getMessage());
        }

        questions = new HomePage(webDriver).scrollDown().getFAQ();
    }

    private void testFaqQuestion(FaqEnum faqEnum, HomePage.FAQ faq) {
        assertEquals(faqEnum.getQuestion(), faq.getQuestion());
        assertEquals(faqEnum.getAnswer(), faq.getAnswer());
    }

    @Test
    public void testPriceAndPaymentFAQ() {
        testFaqQuestion(FaqEnum.PRICE_AND_PAYMENT, questions.get(FaqEnum.PRICE_AND_PAYMENT.ordinal()));
    }


    @Test
    public void testRentMultipleScootersFAQ() {
        testFaqQuestion(FaqEnum.RENT_MULTIPLE_SCOOTERS, questions.get(FaqEnum.RENT_MULTIPLE_SCOOTERS.ordinal()));
    }


    @Test
    public void testRentalTimeCalculationFAQ() {
        testFaqQuestion(FaqEnum.RENTAL_TIME_CALCULATION, questions.get(FaqEnum.RENTAL_TIME_CALCULATION.ordinal()));
    }

    @Test
    public void testRentScooterForTodayFAQ() {
        testFaqQuestion(FaqEnum.RENT_SCOOTER_FOR_TODAY, questions.get(FaqEnum.RENT_SCOOTER_FOR_TODAY.ordinal()));
    }

    @Test
    public void testOrderEditFAQ() {
        testFaqQuestion(FaqEnum.ORDER_EDIT, questions.get(FaqEnum.ORDER_EDIT.ordinal()));
    }

    @Test
    public void testScooterWithChargerFAQ() {
        testFaqQuestion(FaqEnum.SCOOTER_WITH_CHARGER, questions.get(FaqEnum.SCOOTER_WITH_CHARGER.ordinal()));
    }

    @Test
    public void testCancelOrderFAQ() {
        testFaqQuestion(FaqEnum.CANCEL_ORDER, questions.get(FaqEnum.CANCEL_ORDER.ordinal()));
    }

    @Test
    public void testOrderAreaFAQ() {
        testFaqQuestion(FaqEnum.ORDER_AREA, questions.get(FaqEnum.ORDER_AREA.ordinal()));
    }

    @After
    public void tearDown() {
        webDriver.close();
    }

}
