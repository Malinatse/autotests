package org.selenide.examples.baseelements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с радиобоксом
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class Radio extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//label")
    private SelenideElement radioLabel;

    @FindBy(how = How.XPATH, xpath = ".//span")
    private SelenideElement radioSpan;

    /**
     * Получение заголовка радиобокса
     * @return String
     */
    public String getRadioHeader()
    {
        return proxy.getText(radioLabel);
    }

    /**
     * Проверка содержимого заголовка радиобокса
     * @param txt проверяемый текст
     */
    public void checkRadioHeader(String txt)
    {
        proxy.checkText(radioLabel, txt);
    }

    /**
     * Клик по чекбоксу
     */
    public void clickRadio()
    {
        proxy.clickElement(radioLabel);
    }

    public void clickRadioSpan()
    {
        proxy.clickElement(radioSpan);
    }

    public void checkRadio()
    {
        radioSpan.shouldHave(Condition.cssClass("is-checked"));
    }
}
