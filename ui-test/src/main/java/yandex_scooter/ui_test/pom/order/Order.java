package yandex_scooter.ui_test.pom.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import yandex_scooter.ui_test.constants.CommonConstant;
import yandex_scooter.ui_test.constants.Locator;
import yandex_scooter.ui_test.pom.PageObjectModel;
import yandex_scooter.ui_test.pom.ValueAttributeAwareInputField;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Класс описывающий форму заказа относящуюся к арендатору
 */
public class Order extends PageObjectModel {

    private By orderLocator;

    @Getter
    private final Tenant tenant;

    private Rent rent;

    public Order(@NonNull WebDriver webDriver) {
        super(webDriver, By.xpath(Locator.Order.XPATH_ROOT));
        this.orderLocator = super.locator;
        tenant = new Tenant(webDriver);
    }

    /**
     * Класс описывающий часть формы заказа относящуюся к арендатору
     */
    @FieldDefaults(level = AccessLevel.PRIVATE)
    class Tenant extends PageObjectModel {

        @Getter
        final WebElement formTitle;

        final ValueAttributeAwareInputField name;
        final ValueAttributeAwareInputField lastName;
        final ValueAttributeAwareInputField scooterDeliveryAddress;
        final ValueAttributeAwareInputField phoneNumber;
        final PageObjectModel subwayStation;
        final ValueAttributeAwareInputField nextButton;

        Tenant(@NonNull WebDriver webDriver) {
            super(webDriver, orderLocator);

            formTitle = webDriver.findElement(By.className(Locator.Order.Tenant.CLASS_FORM_TITLE));

            // getText() почему-то не возвращает значение даже если sendKeys() установил, поэтому берем из атрибута value
            name = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_NAME_INPUT));
            lastName = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_LASTNAME_INPUT));
            scooterDeliveryAddress = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_SCOOTER_DELIVERY_ADDRESS_INPUT));
            phoneNumber = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_PHONE_NUMBER_INPUT));
            subwayStation = PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Tenant.XPATH_SUBWAY_STATION_INPUT));
            nextButton = new ValueAttributeAwareInputField(webDriver, By.xpath(Locator.Order.Tenant.XPATH_NEXT_BTN));
        }

        public Rent clickNextButton() {
            nextButton.click();

            if(rent == null) {
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
            
            sendKeys(subwayStation);

            WebElement subwayStationDropdown = webDriver.findElement(By.className(Locator.Order.Tenant.CLASS_SUBWAY_STATION_SELECT));

            new WebDriverWait(webDriver, CommonConstant.TIME_OUT)
                    .until(webDriver -> subwayStationDropdown.isDisplayed());

            List<WebElement> foundOptions = subwayStationDropdown.findElements(By.tagName("button"));

            for (WebElement e : foundOptions) {
                if(Objects.equals(e.findElement(By.className(Locator.Order.Tenant.CLASS_SUBWAY_STATION_SELECT_OPTION_VALUE)).getText(), subwayStation)) {
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
            return !this.subwayStation.hasCssClass(Locator.CLASS_INVALID_INPUT_VALUE);
        }

        public boolean isValid() {
            return isNameValid() &&
                   isLastNameValid() &&
                   isScooterDeliveryAddressValid() &&
                   isPhoneNumberValid() &&
                   isSubwayStationValid();
        }
    }

    /**
     * Класс описывающий часть формы заказа относящуюся к аренде
     */
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    class Rent extends PageObjectModel {

        // Система выбрать дату в прошлом и при этом успешно создает заказ.
        // Несмотря на ответ "Только начиная с завтрашнего дня. Но скоро станем расторопнее." на FAQ "Можно ли заказать самокат прямо на сегодня?"
        // система позволяет сделать заказ на будущую дату.
        PageObjectModel scooterDeliveryDate;

        PageObjectModel period;

        // система позволяет выбрать несколько цветов одновременно и при этом успешно создает заказ
        Map<ScooterColor, PageObjectModel> scooterColors;

        PageObjectModel comment;

        PageObjectModel backButton;
        PageObjectModel orderButton;

        Tenant tenant;
        
        Rent(@NonNull WebDriver webDriver, Tenant tenant) {
            super(webDriver, orderLocator);
            this.tenant = tenant;

            scooterDeliveryDate = PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Rent.XPATH_SCOOTER_DELIVERY_DATE_INPUT));
            period = PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Rent.XPATH_PERIOD_INPUT));
            scooterColors = new LinkedHashMap<>();
            scooterColors.put(ScooterColor.BLACK, PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Rent.XPATH_SCOOTER_COLOR_BLACK_CHECKBOX)));
            scooterColors.put(ScooterColor.GRAY, PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Rent.XPATH_SCOOTER_COLOR_GRAY_CHECKBOX)));
            comment = PageObjectModel.wrap(webDriver, By.xpath(Locator.Order.Rent.XPATH_COMMENT_INPUT));
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
            List<WebElement> options =  this.period.findElement(By.className("Dropdown-menu")).findElements(By.tagName("div"));
            for (WebElement option : options) {
                String text = option.getText();
                if(Objects.equals(period, text)) {
                    option.click();
                    break;
                }
            }
            return this;
        }

        public String getPeriod() {
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
    }
}
