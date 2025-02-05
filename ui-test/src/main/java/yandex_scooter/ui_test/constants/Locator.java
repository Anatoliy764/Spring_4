package yandex_scooter.ui_test.constants;

import lombok.experimental.UtilityClass;

/**
 * Класс констант описывающий локаторы графического пользовательского интерфейса "Яндекс самокат"
 * */
@UtilityClass
public class Locator {

    public static final String CLASS_INVALID_INPUT_VALUE = "Input_Error__1Tx5d";
    public static final String CLASS_INVALID_SELECT_OPTION = "Order_MetroError__1BtZb";

    /**
     * Класс констант описывающий локаторы заголовка главной страницы и его компонентов
     * */
    @UtilityClass
    public static class Header {

        /**
         * Заголовок главной страницы
         * */
        public static final String XPATH_ROOT = "//*[@id=\"root\"]/div/div/div[1]";

        /**
         * Логотип "Самокат"
         * */
        public static final String CLASS_SCOOTER_LOGO = "Header_LogoScooter__3lsAR";

        /**
         * Логотип "Яндекс"
         * */
        public static final String CLASS_YANDEX_LOGO = "Header_LogoYandex__3TSOI";

        /**
         * Кнопка "Заказать"
         * */
        public static final String XPATH_ORDER_BUTTON = "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]";

        /**
         * Кнопка "Статус заказа"
         * */
        public static final String XPATH_ORDER_STATUS_BUTTON = "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[2]";

        /**
         * Поле для ввода идентификатора заказа
         * */
        public static final String XPATH_ORDER_ID_INPUT = "//*[@id=\"root\"]/div/div/div[1]/div[3]/div/input";

        /**
         * Кнопка "Go!" запускающая поиск заказа по идентификатору и открывающая форму деталей заказа или форму,
         * сообщающую о том что заказ не найден
         * */
        public static final String XPATH_ORDER_ID_SUBMIT_BUTTON = "//*[@id=\"root\"]/div/div/div[1]/div[3]/button";
    }


    /**
     * Класс констант описывающий локаторы главной страницы и ее компонентов
     * */
    @UtilityClass
    public static class HomePage {

        /**
         * Главная страница
         */
        public static final String XPATH_ROOT = "//*[@id=\"root\"]/div/div";

        /**
         * Кнопка "Заказать" на странице
         */
        public static final String XPATH_ORDER_BTN = "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button";

        /**
         * Текстовый заголовок на главной странице "Самокат на пару дней"
         * */
        public static final String XPATH_TITLE = "//*[@id=\"root\"]/div/div/div[2]/div[4]";

        public static final String XPATH_FAQ = "//*[@id=\"root\"]/div/div/div[5]/div[2]/div";

        public static final String SELECTOR_FAQ_QUESTION_PRICE_AND_PAYMENT = "#accordion__heading-0";
        public static final String SELECTOR_FAQ_ANSWER_PRICE_AND_PAYMENT = "#accordion__panel-0 > p";

        public static final String SELECTOR_FAQ_QUESTION_RENT_MULTIPLE_SCOOTERS = "#accordion__heading-1";
        public static final String SELECTOR_FAQ_ANSWER_RENT_MULTIPLE_SCOOTERS = "#accordion__panel-1 > p";

        public static final String SELECTOR_FAQ_QUESTION_RENTAL_TIME_CALCULATION = "#accordion__heading-2";
        public static final String SELECTOR_FAQ_ANSWER_RENTAL_TIME_CALCULATION = "#accordion__panel-2 > p";

        public static final String SELECTOR_FAQ_QUESTION_RENT_SCOOTER_FOR_TODAY = "#accordion__heading-3";
        public static final String SELECTOR_FAQ_ANSWER_RENT_SCOOTER_FOR_TODAY = "#accordion__panel-3 > p";

        public static final String SELECTOR_FAQ_QUESTION_ORDER_EDIT = "#accordion__heading-4";
        public static final String SELECTOR_FAQ_ANSWER_ORDER_EDIT = "#accordion__panel-4 > p";

        public static final String SELECTOR_FAQ_QUESTION_SCOOTER_WITH_CHARGER = "#accordion__heading-5";
        public static final String SELECTOR_FAQ_ANSWER_SCOOTER_WITH_CHARGER = "#accordion__panel-5 > p";

        public static final String SELECTOR_FAQ_QUESTION_CANCEL_ORDER = "#accordion__heading-6";
        public static final String SELECTOR_FAQ_ANSWER_CANCEL_ORDER = "#accordion__panel-6 > p";

        public static final String SELECTOR_FAQ_QUESTION_ORDER_AREA = "#accordion__heading-7";
        public static final String SELECTOR_FAQ_ANSWER_ORDER_AREA = "#accordion__panel-7 > p";

    }

    /**
     * Класс констант описывающий локаторы страниц статуса заказа.
     * */
    @UtilityClass
    public static class OrderTracking {
        /**
         * Окно с картинкой "Такого заказа нет"
         * */
        public static final String CLASS_ORDER_NOT_FOUND = "Track_NotFound__6oaoY";
        public static final String CSS_SELECTOR_ORDER_NOT_FOUND_IMAGE = "#root > div > div.Track_Content__St6Kn > div.Track_NotFound__6oaoY > img";

        /**
         * Страница деталей существующего заказа
         * */
        public static final String CLASS_ROOT = "Track_OrderColumns__2r_1F";
    }

    /**
     * Класс описывает локаторы всплывающего окна-формы заказа самоката
     * */
    public static class Order {
        /**
         * Всплывающее окно заказа
         */
        public static final String XPATH_ROOT = "//*[@id=\"root\"]/div/div[2]/div[2]";

        /**
         * Класс описывает локаторы элементов всплывающего окна-формы заказа самоката, относящиеся к информации об арендаторе
         * */
        public static class Tenant {

            public static final String CLASS_FORM_TITLE = "Order_Header__BZXOb";

            /**
             * Поле для ввода имени арендатора
             */
            public static final String XPATH_NAME_INPUT = "//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input";

            /**
             * Поле для ввода фамилии арендатора
             */
            public static final String XPATH_LASTNAME_INPUT = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input";


            /**
             * Поле для ввода адреса доставки самоката
             */
            public static final String XPATH_SCOOTER_DELIVERY_ADDRESS_INPUT = "//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input";

            /**
             * Выпадающий список для выбора станции метро
             */
            public static final String XPATH_SUBWAY_STATION_INPUT = "//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div/input";
            public static final String CLASS_SUBWAY_STATION_SELECT_OPTIONS = "select-search__options";
            public static final String CLASS_SUBWAY_STATION_SELECT_OPTION = "Order_Text__2broi";
            public static final String SELECTOR_SUBWAY_STATION_SELECT_OPTION_ERROR = "#root > div > div.Order_Content__bmtHS > div.Order_Form__17u6u > div.Order_UnderError__1VSDB > div.Order_MetroError__1BtZb";
            public static final String XPATH_SUBWAY_STATION_SELECT = "//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div";

            /**
             * Поле для ввода номера телефона арендатора
             */
            public static final String XPATH_PHONE_NUMBER_INPUT = "//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input";

            /**
             * Кнопка "Далее"
             */
            public static final String XPATH_NEXT_BTN = "//*[@id=\"root\"]/div/div[2]/div[3]/button";

        }

        /**
         * Класс описывает локаторы элементов всплывающего окна-формы заказа самоката, относящиеся к информации об аренде
         * */
        public static final class Rent {
            /**
             * Поле для ввода даты доставки самоката
             */
            public static final String XPATH_SCOOTER_DELIVERY_DATE_INPUT = "//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input";
            public static final String CLASS_SCOOTER_DELIVERY_DATE_PICKER = "react-datepicker__month-container";
            public static final String CLASS_SCOOTER_DELIVERY_DATE_PICKER_SELECTED_DAY = "react-datepicker__day--selected";

            public static final String XPATH_PERIOD_INPUT =   "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]";

            /**
             * Чекбокс для выбора черного цвета самоката
             */
            public static final String XPATH_SCOOTER_COLOR_BLACK_CHECKBOX = "//*[@id=\"black\"]";

            /**
             * Чекбокс для выбора серого цвета самоката
             */
            public static final String XPATH_SCOOTER_COLOR_GRAY_CHECKBOX = "//*[@id=\"grey\"]";

            /**
             * Поле для ввода комментария для курьера
             */
            public static final String XPATH_COMMENT_INPUT = "//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input";

            /**
             * Кнопка "Заказать" в форме заказа
             */
            public static final String XPATH_SUBMIT_BTN = "//*[@id=\"root\"]/div/div[2]/div[3]/button[2]";

            /**
             * Кнопка "Назад" в форме заказа
             */
            public static final String XPATH_BACK_BTN = "//*[@id=\"root\"]/div/div[2]/div[3]/button[1]";
        }

        /**
         * Класс описывает локаторы элементов всплывающего окна подтверждения заказа самоката
         * */
        public static final class Confirm {
            /**
             * Окно подтверждения заказа
             */
            public static final String XPATH_ROOT = "//*[@id=\"root\"]/div/div[2]/div[5]";

            /**
             * Кнопка "Да" подтверждающая и создающая заказ
             */
            public static final String XPATH_CONFIRM_BTN = "//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]";

            /**
             * Кнопка "Нет" возвращающая обратно на форму заказа
             */
            public static final String XPATH_CANCEL_BTN = "//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[1]";
        }

        /**
         * Класс описывает локаторы элементов окна с сообщением о результате выполнения размещения заказа
         * */
        public static final class Result {
            /**
             * Окно отображающее результат оформления заказа
             */
            public static final String XPATH_ROOT = "//*[@id=\"root\"]/div/div[2]/div[5]";

            /**
             * Заголовок сообщающий результат оформления заказа
             */
            public static final String XPATH_TITLE = "//*[@id=\"root\"]/div/div[2]/div[5]/div[1]";

            /**
             * Текст содержащий подробную информацию о результате операции, а так же содержащий номер заказа
             */
            public static final String XPATH_DETAILS = "//*[@id=\"root\"]/div/div[2]/div[5]/div[1]/div";

            /**
             * Кнопка "Посмотреть статус" заказа
             */
            public static final String XPATH_SHOW_STATUS_BTN = "//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button";
        }
    }

}
