package yandex_scooter.ui_test.util;

import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Arrays;
import java.util.logging.Level;

@Log
@UtilityClass
public class WebDriverFactory {

    private static final String ENV_BROWSER = "BROWSER";
    private static final String ENV_BROWSER_OPTIONS = "BROWSER_OPTIONS";
    private static final String ENV_BROWSER_WINDOW_X = "BROWSER_WINDOW_COORDINATE_X";
    private static final String ENV_BROWSER_WINDOW_Y = "BROWSER_WINDOW_COORDINATE_Y";
    private static final String ENV_BROWSER_WINDOW_WIDTH = "BROWSER_WINDOW_WIDTH";
    private static final String ENV_BROWSER_WINDOW_HEIGHT = "BROWSER_WINDOW_HEIGHT";

    public enum Browser {
        CHROME,
        FIREFOX,
        EDGE,
        SAFARI;
    }

    public static WebDriver create(Browser browser, String ... options) {
        switch (browser) {
            case CHROME: {
                return new ChromeDriver((ChromeOptions) createOptions(browser, options));
            }
            case FIREFOX: {
                return new FirefoxDriver((FirefoxOptions) createOptions(browser, options));
            }
            case EDGE: {
                return new EdgeDriver((EdgeOptions) createOptions(browser, options));
            }
            case SAFARI: {
                return new SafariDriver(SafariOptions.fromCapabilities(createOptions(browser, options)));
            }
            default: throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static WebDriver create() {

        WebDriver webDriver;
        Browser browser;
        String[] options = null;
        if(!System.getenv().containsKey(ENV_BROWSER)) {
            throw new IllegalStateException("Required environment variable " + ENV_BROWSER + " is not set.");
        } else {
            try {
                browser = Browser.valueOf(System.getenv(ENV_BROWSER).toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Required environment variable " + ENV_BROWSER + " is invalid. Supported browsers: " + Arrays.toString(Browser.values()));
            }
        }
        if(System.getenv().containsKey(ENV_BROWSER_OPTIONS)) {
            options = System.getenv(ENV_BROWSER_OPTIONS).split("\\s+");
            if(options == null || options.length == 0) {
                log.log(Level.WARNING, "Warning. Environment variable " + ENV_BROWSER_OPTIONS + " is present, but empty or invalid. Options should be split by space character.");
            }
        }

        webDriver = create(browser, options);

        try {
            if(System.getenv().containsKey(ENV_BROWSER_WINDOW_X) && System.getenv().containsKey(ENV_BROWSER_WINDOW_Y)) {
                int x = Integer.parseInt(System.getenv(ENV_BROWSER_WINDOW_X));
                int y = Integer.parseInt(System.getenv(ENV_BROWSER_WINDOW_Y));
                webDriver.manage().window().setPosition(new Point(x, y));
            }
            if(System.getenv().containsKey(ENV_BROWSER_WINDOW_WIDTH) && System.getenv().containsKey(ENV_BROWSER_WINDOW_HEIGHT)) {
                int w = Integer.parseInt(System.getenv(ENV_BROWSER_WINDOW_WIDTH));
                int h = Integer.parseInt(System.getenv(ENV_BROWSER_WINDOW_HEIGHT));
                webDriver.manage().window().setSize(new Dimension(w, h));
            } else {
                webDriver.manage().window().maximize();
            }
        } catch (NumberFormatException e) {
            log.log(Level.WARNING, "Browser positioning environment variables are present, but invalid. Should be integer digits");
        }

        return webDriver;
    }

    private static AbstractDriverOptions createOptions(Browser browser, String[] options) {
        if(browser == null) {
            throw new IllegalArgumentException("browser may not be null");
        }
        switch (browser) {
            case CHROME: {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(options);
                return chromeOptions;
            }
            case FIREFOX: {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(options);
                return firefoxOptions;
            }
            case EDGE: {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments(options);
                return edgeOptions;
            }
            case SAFARI: {
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setUseTechnologyPreview(true);
                return safariOptions;
            }
            default: return null;
        }
    }
}
