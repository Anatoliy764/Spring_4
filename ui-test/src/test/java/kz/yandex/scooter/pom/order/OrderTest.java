package kz.yandex.scooter.pom.order;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.pom.home.HomePage;
import kz.yandex.scooter.util.WebDriverFactory;

import java.time.ZoneId;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class OrderTest {

    /*
     *  <blockquote
     *      <p>Нужно написать тесты с разными данными: минимум два набора. Какие именно данные использовать — на твоё усмотрение.</p>
     *  </blockquote>
     *
     *  Вместо написания одних и тех же тестов с разными наборами данных используется Java faker.
     *  При каждом запуске теста набор данных будет сгенерирован.
     * */
    private static final Faker FAKER = new Faker(new Locale("ru", "RU"));
    private static final Logger log = Logger.getLogger(OrderTest.class.getName());

    @Test
    public void testOrderFromHeaderShouldSuccessfullyCreated() {

        WebDriver driver = null;
        try {

            driver = WebDriverFactory.create();
            driver.get(CommonConstant.URL_YANDEX_SCOOTER);

            Order.Tenant tenant = new HomePage(driver).getHeader().clickOrderButton().getTenant()
                    .setName(FAKER.name().firstName())
                    .setLastName(FAKER.name().lastName())
                    .setScooterDeliveryAddress(FAKER.address().streetAddress())
                    .setSubwayStation(SubwayStation.random().getName())
                    .setPhoneNumber(FAKER.phoneNumber().phoneNumber().replaceAll("[^\\d+]", ""));

            log.info(tenant.toString());

            Order.Rent rent = tenant.clickNextButton()
                    .setComment(FAKER.hobbit().quote())
                    .setPeriod(RentPeriod.random().getName())
                    .setScooterDeliveryDate(FAKER.date().future(1, TimeUnit.DAYS).toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate())
                    .checkScooterColor(ScooterColor.GRAY);

            log.info(rent.toString());

            Order.Result orderResult = rent.clickOrderButton().clickYesButton();

            assertTrue(orderResult.isSuccess());

            Long orderId = orderResult.getOrderId();
            log.log(Level.INFO, "Order ID: " + orderId);
            assertNotNull(orderId);

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to instantiate chrome driver. Reason: " + e.getMessage());
        } finally {
            if (driver != null) {
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

            Order.Tenant tenant = new HomePage(driver).scrollDown().clickOrderButton().getTenant()
                    .setName(FAKER.name().firstName())
                    .setLastName(FAKER.name().lastName())
                    .setScooterDeliveryAddress(FAKER.address().streetAddress())
                    .setSubwayStation(SubwayStation.random().getName())
                    .setPhoneNumber(FAKER.phoneNumber().phoneNumber().replaceAll("[^\\d+]", ""));

            log.info(tenant.toString());

            Order.Rent rent = tenant.clickNextButton()
                    .setComment(FAKER.hobbit().quote())
                    .setPeriod(RentPeriod.random().getName())
                    .setScooterDeliveryDate(FAKER.date().future(1, TimeUnit.DAYS).toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate())
                    .checkScooterColor(ScooterColor.GRAY);

            log.info(rent.toString());

            Order.Result orderResult = rent.clickOrderButton().clickYesButton();

            assertTrue(orderResult.isSuccess());

            Long orderId = orderResult.getOrderId();
            log.log(Level.INFO, "Order ID: " + orderId);
            assertNotNull(orderId);

        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to instantiate chrome driver. Reason: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.close();
            }
        }
    }
}
