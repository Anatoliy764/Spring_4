package yandex_scooter.ui_test.pom.home;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.util.WebDriverFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Дополнительные тестовые сценарии
 * Проверить: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeaderTestYandexLogo {

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
        webDriver.close();
    }

    @Test
    public void testClickYandexLogoShouldOpenNewTab() {

        try {
            new Header(webDriver).clickYandexLogo();
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        Set<String> windowHandles = webDriver.getWindowHandles();

        assertEquals(2, windowHandles.size());
    }

    @Test
    public void testClickYandexLogoShouldLinkToYandexMainPage() {

        Set<String> tabsBeforeClick = webDriver.getWindowHandles();

        try {
            new Header(webDriver).clickYandexLogo();
        } catch (IllegalStateException e) {
            fail(e.getMessage());
        }

        Set<String> tabsAfterClick = webDriver.getWindowHandles();

        // убеждаемся что открылась только 1 новая вкладка
        assertEquals(2, tabsAfterClick.size());

        tabsAfterClick.removeAll(tabsBeforeClick);

        for (String tab : tabsAfterClick) {
            webDriver.switchTo().window(tab);
            assertEquals(CommonConstant.URL_YANDEX, webDriver.getCurrentUrl());
        }
    }
}