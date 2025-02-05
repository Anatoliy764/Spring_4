package yandex_scooter.ui_test.pom.order;

import com.github.javafaker.Faker;
import lombok.extern.java.Log;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.pom.home.HomePage;
import yandex_scooter.ui_test.util.WebDriverFactory;

import java.time.ZoneId;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Log
public class OrderTest {

    private static final Faker FAKER = new Faker(new Locale("ru", "RU"));

    @Test
    public void testOrderFromHeaderShouldSuccessfullyCreated() {

        WebDriver driver = null;
        try {
            driver = WebDriverFactory.create();
            driver.get(CommonConstant.URL_YANDEX_SCOOTER);

            Order.Result orderResult = new HomePage(driver)
                    .getHeader()
                    .clickOrderButton()
                    .getTenant()
                        .setName(FAKER.name().firstName())
                        .setLastName(FAKER.name().lastName())
                        .setScooterDeliveryAddress(FAKER.address().streetAddress())
                        .setSubwayStation(SubwayStation.random().getName())
                        .setPhoneNumber(FAKER.phoneNumber().phoneNumber().replaceAll("[^\\d+]", ""))
                    .clickNextButton()
                        .setComment(FAKER.hobbit().quote())
                        .setPeriod(RentPeriod.random().getName())
                        .setScooterDeliveryDate(FAKER.date().future(1, TimeUnit.DAYS).toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate())
                        .checkScooterColor(ScooterColor.GRAY)
                    .clickOrderButton()
                    .clickYesButton();

            assertTrue(orderResult.isSuccess());

            Long orderId = orderResult.getOrderId();
            log.log(Level.INFO, "Order ID: " + orderId);
            assertNotNull(orderId);

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to instantiate chrome driver. Reason: " + e.getMessage());
        } finally {
            if(driver != null) {
                driver.close();
            }
        }
    }

    @Test
    public void testOrderShouldSuccessfullyCreated() {

        WebDriver driver = null;
        try {
            driver = WebDriverFactory.create();
            driver.get(CommonConstant.URL_YANDEX_SCOOTER);

            Order.Result orderResult = new HomePage(driver)
                    .scrollDown()
                    .clickOrderButton()
                    .getTenant()
                        .setName(FAKER.name().firstName())
                        .setLastName(FAKER.name().lastName())
                        .setScooterDeliveryAddress(FAKER.address().streetAddress())
                        .setSubwayStation(SubwayStation.random().getName())
                        .setPhoneNumber(FAKER.phoneNumber().phoneNumber().replaceAll("[^\\d+]", ""))
                    .clickNextButton()
                        .setComment(FAKER.hobbit().quote())
                        .setPeriod(RentPeriod.random().getName())
                        .setScooterDeliveryDate(FAKER.date().future(1, TimeUnit.DAYS).toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate())
                        .checkScooterColor(ScooterColor.GRAY)
                    .clickOrderButton()
                    .clickYesButton();

            assertTrue(orderResult.isSuccess());
            Long orderId = orderResult.getOrderId();
            log.log(Level.INFO, "Order ID: " + orderId);
            assertNotNull(orderId);

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to instantiate chrome driver. Reason: " + e.getMessage());
        } finally {
            if(driver != null) {
                driver.close();
            }
        }
    }
}
