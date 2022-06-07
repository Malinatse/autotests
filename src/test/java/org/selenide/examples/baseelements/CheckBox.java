package org.selenide.examples.baseelements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

/**
 * Объект для взаимодействия с чекбоксом
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class CheckBox extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "label")
    private SelenideElement boxHeader;

    @FindBy(how = How.XPATH, xpath = "label/span")
    private SelenideElement boxCheck;

    /**
     * Получение заголовка чекбоква
     * @return String
     */
    public String getBoxHeader()
    {
        return proxy.getText(boxHeader);
    }

    /**
     * Проверка содержимого заголовка текстбокса
     * @param txt проверяемый текст
     */
    public void checkBoxHeader(String txt)
    {
        proxy.checkText(boxHeader, txt);
    }

    /**
     * Клик по чекбоксу
     */
    public void clickCheckBox()
    {
        proxy.clickElement(boxCheck);
    }

}
