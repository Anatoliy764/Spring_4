package kz.yandex.scooter.pom.order;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import kz.yandex.scooter.constants.CommonConstant;
import kz.yandex.scooter.constants.Locator;
import kz.yandex.scooter.pom.PageObjectModel;
import kz.yandex.scooter.pom.ValueAttributeAwareInputField;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Класс описывающий форму заказа относящуюся к арендатору
 */
public class Order extends PageObjectModel {

    private By orderLocator;

    private final Tenant tenant;

    private Rent rent;

    public Order(@NonNull WebDriver webDriver) {
        super(webDriver, By.xpath(Locator.Order.XPATH_ROOT));
        this.orderLocator = super.locator;
        tenant = new Tenant(webDriver);
    }

    @Override
    public String toString() {
        return "Order{" +
               "tenant=" + tenant +
               ", rent=" + rent +
               '}';
    }

    public Tenant getTenant() {
        return this.tenant;
    }

    /**
     * Класс описывающий часть формы заказа относящуюся к арендатору
     */
    class Tenant extends PageObjectModel {

        private final WebElement formTitle;

        private final ValueAttributeAwareInputField name;
        private final ValueAttributeAwareInputField lastName;
        private final ValueAttributeAwareInputField scooterDeliveryAddress;
        private final ValueAttributeAwareInputField phoneNumber;
        private final ValueAttributeAwareInputField subwayStation;
        private final ValueAttributeAwareInputField nextButton;

        Tenant(@NonNull WebDriver webDriver) {
            super(webDriver, orderLocator);

            formTitle = webDriver.findElement(By.className(Locator.Order.Tenant.CLASS_FORM_TITLE));

            // getText() почему-то не возвращает значение даже если sendKeys() установил, поэтому берем из атрибута value
            name = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_NAME_INPUT));
            lastName = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_LASTNAME_INPUT));
            scooterDeliveryAddress = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_SCOOTER_DELIVERY_ADDRESS_INPUT));
            phoneNumber = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_PHONE_NUMBER_INPUT));
            subwayStation = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_SUBWAY_STATION_INPUT));
            nextButton = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_NEXT_BTN));
        }

        public Rent clickNextButton() {
            nextButton.click();

            if (rent == null) {
                rent = new Rent(webDriver, this);
            }
            return rent;
        }

        public Tenant setName(String name) {
            this.name.sendKeys(name);
            return this;
        }

        public String getName() {
            return this.name.getValueAttribute();
        }


        public Tenant setLastName(String lastName) {
            this.lastName.sendKeys(lastName);
            return this;
        }

        public String getLastName() {
            return lastName.getText();
        }


        public Tenant setScooterDeliveryAddress(String address) {
            this.scooterDeliveryAddress.sendKeys(address);
            return this;
        }

        public String getScooterDeliveryAddress() {
            return scooterDeliveryAddress.getText();
        }


        public Tenant setPhoneNumber(String phoneNumber) {
            this.phoneNumber.sendKeys(phoneNumber);
            return this;
        }

        public String getPhoneNumber() {
            return phoneNumber.getText();
        }

        public Tenant setSubwayStation(String subwayStation) {

            this.subwayStation.sendKeys(subwayStation);

            WebElement subwayStationDropdown = webDriver.findElement(By.className(Locator.Order.Tenant.CLASS_SUBWAY_STATION_SELECT_OPTIONS));

            new WebDriverWait(webDriver, CommonConstant.TIME_OUT)
                    .until(webDriver -> subwayStationDropdown.isDisplayed());

            List<WebElement> foundOptions = subwayStationDropdown.findElements(By.tagName("button"));

            for (WebElement e : foundOptions) {
                if (Objects.equals(e.findElement(By.className(Locator.Order.Tenant.CLASS_SUBWAY_STATION_SELECT_OPTION)).getText(), subwayStation)) {
                    e.click();
                    break;
                }
            }
            return this;
        }

        public String getSubwayStation() {
            return subwayStation.getText();
        }

        public boolean isNameValid() {
            return !this.name.hasCssClass(Locator.CLASS_INVALID_INPUT_VALUE);
        }

        public boolean isLastNameValid() {
            return !this.lastName.hasCssClass(Locator.CLASS_INVALID_INPUT_VALUE);
        }

        public boolean isScooterDeliveryAddressValid() {
            return !this.scooterDeliveryAddress.hasCssClass(Locator.CLASS_INVALID_INPUT_VALUE);
        }

        public boolean isPhoneNumberValid() {
            return !this.phoneNumber.hasCssClass(Locator.CLASS_INVALID_INPUT_VALUE);
        }

        public boolean isSubwayStationValid() {
            try {
                return !webDriver.findElement(By.ByCssSelector.cssSelector(Locator.Order.Tenant.SELECTOR_SUBWAY_STATION_SELECT_OPTION_ERROR))
                        .getDomAttribute("class")
                        .equals(Locator.CLASS_INVALID_SELECT_OPTION);
            } catch (NoSuchElementException ignored) {
                return true;
            }
        }

        public boolean isValid() {
            return isNameValid() &&
                   isLastNameValid() &&
                   isScooterDeliveryAddressValid() &&
                   isPhoneNumberValid() &&
                   isSubwayStationValid();
        }

        @Override
        public String toString() {
            return "Tenant{" +
                   "name=" + getName() +
                   ", lastName=" + getLastName() +
                   ", scooterDeliveryAddress=" + getScooterDeliveryAddress() +
                   ", subwayStation=" + getSubwayStation() +
                   ", phoneNumber=" + getPhoneNumber() +
                   '}';
        }

        public WebElement getFormTitle() {
            return this.formTitle;
        }
    }

    /**
     * Класс описывающий часть формы заказа относящуюся к аренде
     */
    class Rent extends PageObjectModel {

        // Система позволяет выбрать дату в прошлом и при этом успешно создает заказ.
        private final ValueAttributeAwareInputField scooterDeliveryDate;

        private final PageObjectModel period;

        // система позволяет выбрать несколько цветов одновременно и при этом успешно создает заказ
        private final Map<ScooterColor, PageObjectModel> scooterColors;

        private final ValueAttributeAwareInputField comment;

        private final PageObjectModel backButton;
        private final PageObjectModel orderButton;

        private final Tenant tenant;

        Rent(@NonNull WebDriver webDriver, Tenant tenant) {
            super(webDriver, orderLocator);
            this.tenant = tenant;

            scooterDeliveryDate = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Rent.XPATH_SCOOTER_DELIVERY_DATE_INPUT));
            period = PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Rent.XPATH_PERIOD_INPUT));
            scooterColors = new LinkedHashMap<>();
            scooterColors.put(ScooterColor.BLACK, PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Rent.XPATH_SCOOTER_COLOR_BLACK_CHECKBOX)));
            scooterColors.put(ScooterColor.GRAY, PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Rent.XPATH_SCOOTER_COLOR_GRAY_CHECKBOX)));
            comment = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Rent.XPATH_COMMENT_INPUT));
            backButton = PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Rent.XPATH_BACK_BTN));
            orderButton = PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Rent.XPATH_SUBMIT_BTN));
        }

        public Tenant clickBackButton() {
            backButton.click();
            // ждем пока форма "Для кого самокат" не отобразится
            new WebDriverWait(webDriver, CommonConstant.TIME_OUT)
                    .until(d -> tenant.isDisplayed());
            return tenant;
        }

        public Confirm clickOrderButton() {
            orderButton.click();

            return new Confirm(webDriver, this);
        }

        public Rent setScooterDeliveryDate(LocalDate localDate) {
            return setScooterDeliveryDate(localDate.format(CommonConstant.DATE_FORMATTER));
        }

        public Rent setScooterDeliveryDate(String date) {
            scooterDeliveryDate.sendKeys(date);

            new WebDriverWait(webDriver, CommonConstant.TIME_OUT)
                    .until(d -> d.findElement(By.className(Locator.Order.Rent.CLASS_SCOOTER_DELIVERY_DATE_PICKER)).isDisplayed());

            WebElement selectedDay = webDriver.findElement(By.className(Locator.Order.Rent.CLASS_SCOOTER_DELIVERY_DATE_PICKER_SELECTED_DAY));

            selectedDay.click();

            return this;
        }

        public String getScooterDeliveryDate() {
            return scooterDeliveryDate.getText();
        }

        public boolean isScooterDeliveryDateValid() {
            String value = scooterDeliveryDate.getText();

            boolean isValid = value != null &&
                              !value.isEmpty() &&
                              !scooterDeliveryDate.hasCssClass(Locator.CLASS_INVALID_INPUT_VALUE);
            try {
                CommonConstant.DATE_FORMATTER.parse(value, LocalDate::from);
            } catch (DateTimeParseException e) {
                isValid = false;
            }
            return isValid;
        }

        public Rent setPeriod(String period) {
            this.period.click();
            List<WebElement> options = this.period.findElement(By.className("Dropdown-menu")).findElements(By.tagName("div"));
            for (WebElement option : options) {
                String text = option.getText();
                if (Objects.equals(period, text)) {
                    option.click();
                    break;
                }
            }
            return this;
        }

        public String getPeriod() {
            this.period.getText();
            return webDriver.findElement(By.xpath(Locator.Order.Rent.XPATH_PERIOD_INPUT)).getText();
        }

        public Rent checkScooterColor(ScooterColor color) {
            new WebDriverWait(webDriver, CommonConstant.TIME_OUT).until(webDriver -> isDisplayed());
            this.scooterColors.get(color).click();
            return this;
        }

        public Rent uncheckScooterColor(ScooterColor color) {
            return checkScooterColor(color);
        }

        public Rent setComment(String comment) {
            this.comment.sendKeys(comment);
            return this;
        }

        public String getComment() {
            return comment.getText();
        }

        /*
         * Обязательные поля для ввода в форме "Про аренду" не валидируются как в форме "Для кого самокат",
         * т.е. поля не подсвечиваются красным цветом и нет сообщений об ошибке, однако если поля пусты, то кнопка "Заказать" не работет.
         * */
        public boolean isValid() {
            return !getScooterDeliveryDate().isEmpty() && !getPeriod().isEmpty();
        }

        @Override
        public String toString() {
            return "Rent{" +
                   "scooterDeliveryDate=" + getScooterDeliveryDate() +
                   ", period=" + getPeriod() +
                   ", scooterColors=" + (scooterColors != null ? scooterColors.keySet().stream().map(ScooterColor::getName).collect(Collectors.joining()) : null) +
                   ", comment=" + getComment() +
                   '}';
        }
    }

    /**
     * Класс описывающий форму подтверждения заказа
     */
    class Confirm extends PageObjectModel {
        private final WebElement noButton;
        private final WebElement yesButton;
        private final Rent rent;

        Confirm(WebDriver driver, Rent rent) {
            super(driver, By.xpath(Locator.Order.Confirm.XPATH_ROOT));
            noButton = driver.findElement(By.xpath(Locator.Order.Confirm.XPATH_CANCEL_BTN));
            yesButton = driver.findElement(By.xpath(Locator.Order.Confirm.XPATH_CONFIRM_BTN));
            this.rent = rent;
        }

        public Rent clickNoButton() {
            if (!noButton.isDisplayed()) {
                throw new IllegalStateException("Кнопка \"Нет\" не отображена");
            }
            if (!noButton.isEnabled()) {
                throw new IllegalStateException("Кнопка \"Нет\" не доступна для нажатия");
            }
            noButton.click();

            return rent;
        }

        public Result clickYesButton() {
            if (!yesButton.isEnabled()) {
                throw new IllegalStateException("Yes button is not enabled");
            }
            yesButton.click();

            return new Result(webDriver);
        }
    }

    /**
     * Класс описывающий окно показывающее результат выполнения заказа
     */
    class Result extends PageObjectModel {
        private final Pattern ORDER_ID_PATTERN = Pattern.compile("Номер заказа: (\\d+)");
        private final String SUCCESS_ORDER_TITLE = "Заказ оформлен";

        private final WebElement title;
        private final WebElement details;

        private final WebElement statusButton;

        Result(WebDriver driver) {
            super(driver, By.xpath(Locator.Order.Result.XPATH_ROOT));
            title = driver.findElement(By.xpath(Locator.Order.Result.XPATH_TITLE));
            details = driver.findElement(By.xpath(Locator.Order.Result.XPATH_DETAILS));
            statusButton = driver.findElement(By.xpath(Locator.Order.Result.XPATH_SHOW_STATUS_BTN));
        }

        public String getTitle() {
            return title.getText();
        }

        public String getDetails() {
            return details.getText();
        }

        public boolean isSuccess() {
            String t = getTitle();
            return t != null && !t.isBlank() && t.contains(SUCCESS_ORDER_TITLE);
        }

        public Long getOrderId() {

            Long orderId = null;

            new WebDriverWait(webDriver, CommonConstant.TIME_OUT)
                    .until(driver ->
                            getDetails() != null &&
                            getDetails().replace("\n", "").matches(".*\\d.*"));

            Matcher matcher = ORDER_ID_PATTERN.matcher(getDetails());

            if (matcher.find()) {
                orderId = Long.parseLong(matcher.group(1));
            }
            return orderId;
        }

        public void showStatus() {
            statusButton.click();
        }
    }
}
