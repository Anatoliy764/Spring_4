package kz.yandex.scooter.pom.home;

import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.util.WebDriverFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqTest {

    private static WebDriver webDriver;

    @Parameterized.Parameter(0)
    public int questionOrderIndex;

    @Parameterized.Parameter(1)
    public HomePage.FAQ faq;

    @Parameterized.Parameters
    public static Collection<Object[]> getQuestions() {
        try {
            webDriver = WebDriverFactory.create();
            webDriver.get(CommonConstant.URL_YANDEX_SCOOTER);

            List<HomePage.FAQ> faqList = new HomePage(webDriver).scrollDown().getFAQ();

            Collection<Object[]> data = new ArrayList<>();
            for (int i = 0; i < faqList.size(); i++) {
                data.add(new Object[]{i, faqList.get(i)});
            }
            return data;

        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate chrome webDriver. Reason: " + e.getMessage());
        }
    }

    @Test
    public void test() {
        assertEquals(FaqEnum.valueOf(questionOrderIndex).getAnswer(), faq.getAnswer());
    }

    @AfterClass
    public static void tearDown() {
        webDriver.close();
    }

}
