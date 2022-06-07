package org.selenide.examples.baseelements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с полем ввода данных и кнопокой удаления поля
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class TextInputWithDel extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "div[2]")
    private SelenideElement deleteBtn;

    @FindBy(how = How.XPATH, xpath = "div[1]/div[1]/input")
    private SelenideElement inputField;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());


    /**
     * Установка текстового значения поля
     * @param txt искомый текст
     * @param enterPress флаг необходимости нажатия клавиши Enter
     */
    public void setInput(String txt, Boolean enterPress)
    {
        proxy.setText(inputField, txt, enterPress);
    }

    /**
     * Установка текстового значения поля после очищения его содержимого
     * @param txt искомый текст
     * @param enterPress флаг необходимости нажатия клавиши Enter
     */
    public void setClearInput(String txt, Boolean enterPress)
    {
        proxy.setClearText(inputField, txt, enterPress);
    }

    /**
     * Проверка содержимого поля Input
     * @param txt ожидаемый текст
     */
    public void checkInputValue(String txt)
    {
        proxy.checkTextFromInput(inputField, txt);
    }

    /**
     * Нажатие на кнопку удаления поля
     */
    public void clickDelete()
    {
        proxy.clickElement(deleteBtn);
    }
}
