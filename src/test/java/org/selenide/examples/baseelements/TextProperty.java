package org.selenide.examples.baseelements;

import com.codeborne.selenide.ElementsContainer;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с текстовым полем
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class TextProperty extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Получение текста заголовка поля
     * @return String
     */
    public String getPropertyHeaderText()
    {
        return proxy.getText(getSelf()).split(":")[0];
    }

    /**
     * Получение текста поля
     * @return String
     */
    public String getPropertyText()
    {
        return proxy.getText(getSelf()).split(":")[1];
    }

    /**
     * Проверка текста
     * @param txt проверяемый текст
     */
    public void checkPropertyHeader(String txt)
    {
        proxy.checkText(getSelf(), txt);
    }

    public void clickPropertyHeader()
    {
        proxy.clickElement(getSelf());
    }
}
