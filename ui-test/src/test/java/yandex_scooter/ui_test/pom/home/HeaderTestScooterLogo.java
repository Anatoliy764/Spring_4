package yandex_scooter.ui_test.pom.home;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.constants.Locator;
import yandex_scooter.ui_test.util.WebDriverFactory;

import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Дополнительные тестовые сценарии
 * Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeaderTestScooterLogo {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        try {
            webDriver = WebDriverFactory.create();
            webDriver.get(CommonConstant.URL_YANDEX_SCOOTER);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось инициализировать WebDriver. Причина: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void testClickScooterLogoShouldNotOpenNewTab() {

        try {
            new Header(webDriver).clickScooterLogo();
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        Set<String> windowHandles = webDriver.getWindowHandles();

        assertEquals(1, windowHandles.size());
    }

    @Test
    public void testClickScooterLogoShouldLinkToMainURL() {

        Set<String> tabsBeforeClick = webDriver.getWindowHandles();

        try {
            new Header(webDriver).clickScooterLogo();
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        Set<String> tabsAfterClick = webDriver.getWindowHandles();

        tabsAfterClick.removeAll(tabsBeforeClick);

        for (String tab : tabsAfterClick) {
            webDriver.switchTo().window(tab);
            assertEquals(CommonConstant.URL_YANDEX_SCOOTER, webDriver.getCurrentUrl());
        }
    }

    @Test
    public void testclickScooterLogoShouldOpenHomePage() {
        Set<String> tabsBeforeClick = webDriver.getWindowHandles();

        try {
            new Header(webDriver).clickScooterLogo();
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        Set<String> tabsAfterClick = webDriver.getWindowHandles();

        tabsAfterClick.removeAll(tabsBeforeClick);

        for (String tab : tabsAfterClick) {
            webDriver.switchTo().window(tab);
            try {
                WebElement element = webDriver.findElement(By.xpath(Locator.HomePage.XPATH_TITLE));
                assertEquals("Самокат <br>на&nbsp;пару дней", element.getText());
            } catch (Exception e) {
                fail("Home page element not found on current visible page");
            }
        }
    }
}