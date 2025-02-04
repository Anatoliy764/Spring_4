package yandex_scooter.ui_test.constants;

import lombok.experimental.UtilityClass;

/**
 * Класс констант описывающий локаторы графического пользовательского интерфейса "Яндекс самокат"
 * */
@UtilityClass
public class Locator {

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
         * Текстовый заголовок на главной странице "Самокат на пару дней"
         * */
        public static final String XPATH_TITLE = "//*[@id=\"root\"]/div/div/div[2]/div[4]";

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

}
