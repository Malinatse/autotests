package org.selenide.examples.baseelements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с ползунком выбора цвета
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class ColorPicker extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//span")
    private SelenideElement colorPickerHeader;

    @FindBy(how = How.XPATH, xpath = "//div/div/div[1]/span")
    private SelenideElement colorCode;

    @FindBy(how = How.XPATH, xpath = "//div/div/div[2]/input")
    private SelenideElement colorRainBowInput;

    /**
     * Проверка заголовка колорпикера
     * @param txt ожидаемый заголовок
     */
    public void checkPickerHeader(String txt)
    {
        proxy.checkText(colorPickerHeader, txt);
    }

    /**
     * Проверка кода цвета
     * @param txt ожидаемый код
     */
    public void checkColorCode(String txt)
    {
        proxy.checkText(colorCode, txt);
    }

    /**
     * Получение кода цвета
     * @return String
     */
    public String getColorCode()
    {
        return proxy.getText(colorCode);
    }

    /**
     * Установка цвета группы
     * @param val значение от 0 до 360
     * @param enterPress признак необходимости нажатия клавиши enter
     */
    public void setRainbowValue(String val, Boolean enterPress)
    {
        proxy.setClearText(colorRainBowInput, val, enterPress);
    }
}
