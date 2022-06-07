package org.selenide.examples.baseelements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.html.primitives.IError;
import org.selenide.examples.baseelements.html.primitives.IHeader;
import org.selenide.examples.baseelements.html.primitives.IInput;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с полем ввода данных
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class TextInput extends ElementsContainer implements IInput, IError, IHeader {
    @FindBy(how = How.XPATH, xpath = "label")
    private SelenideElement inputHeader;

    @FindBy(how = How.XPATH, xpath = ".//input")
    private SelenideElement inputField;

    @FindBy(how = How.XPATH, xpath = ".//span")
    private SelenideElement inputSpan;

    @FindBy(how = How.XPATH, xpath = "div[2]")
    private SelenideElement inputError;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Проверка заголовка поля ввода
     * @param value искомый текст
     */
    public void checkHeader(String value)
    {
        proxy.checkText(inputHeader, value);
    }

    /**
     * Проверка текста сообщения об ошибке
     * @param value искомый текст
     */
    public void checkError(String value)
    {
        proxy.checkText(inputError, value);
    }

    /**
     * Проверка текста сообщения об ошибке
     * @param exists флаг существования
     */
    public void checkErrorExistence(Boolean exists)
    {
        proxy.checkExistence(inputError, exists);
    }

    /**
     * Установка текстового значения поля
     * @param txt искомый текст
     * @param enterPress флаг необходимости нажатия клавиши Enter
     */
    public void setInput(String txt, Boolean enterPress)
    {
        proxy.setTextForInput(inputField, txt, enterPress);
    }

    /**
     * Установка текстового значения поля после очищения его содержимого
     * @param txt искомый текст
     * @param enterPress флаг необходимости нажатия клавиши Enter
     */
    public void setClearInput(String txt, Boolean enterPress)
    {
        proxy.setClearTextForInput(inputField, txt, enterPress);
    }

    /**
     * Проверка содержимого поля Input
     * @param txt ожидаемый текст
     */
    public void checkInputValue(String txt)
    {
        proxy.checkTextFromInput(inputField, txt);
    }

    public void checkInputSpan(String txt)
    {
        proxy.checkText(inputSpan, txt);
    }

    public void clickInput()
    {
        proxy.clickElement(inputField);
    }
}
