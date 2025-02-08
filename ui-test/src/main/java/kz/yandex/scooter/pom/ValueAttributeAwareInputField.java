package kz.yandex.scooter.pom;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import kz.yandex.scooter.constants.CommonConstant;

import java.util.Objects;

/**
 * Класс описывает UI поле для ввода.
 * Даже если sendKeys() устанавливает значение в поле для ввода, getText() возвращает пустую строку, а значение на самом деле присутствует в атрибуте "value".
 * Данный класс помогает извлечь значение
 */
public class ValueAttributeAwareInputField extends PageObjectModel {
    public ValueAttributeAwareInputField(@NonNull WebDriver webDriver, @NonNull By locator) {
        super(webDriver, locator);
    }

    @Override
    public String getText() {
        String text = super.getText();
        if (text.isEmpty()) {
            text = getValueAttribute();
        }
        return text;
    }

    @Override
    public PageObjectModel clear() {
        validateState();
        webElement.clear();
        if (!getValueAttribute().isEmpty()) {
            setValueAttribute("");
        }
        new WebDriverWait(webDriver, CommonConstant.TIME_OUT).until(d -> getText().isEmpty());
        return this;
    }

    @Override
    public PageObjectModel sendKeys(String keys) {
        this.clear();
        webElement.sendKeys(keys);
        new WebDriverWait(webDriver, CommonConstant.TIME_OUT).until(d -> Objects.equals(this.getText(), keys));
        return this;
    }
}
