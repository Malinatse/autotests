package org.selenide.examples.baseelements;

import com.codeborne.selenide.ElementsContainer;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;

import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с кнопкой
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class Button <PageObjectClass> extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Получение текста кнопки
     * @return String
     */
    public String getButtonText()
    {
        return proxy.getText(getSelf());
    }

    /**
     * Клик по кнопке
     */
    public void clickButton()
    {
        proxy.clickElement(getSelf());
    }

    /**
     * Клик на кнопку с последующим открытием новой страницы
     * @param pageObjectClass тип возвращаемой страницы
     * @return новый экземпляр класса Page object ожидаемой страницы
     */
    public PageObjectClass clickButton(Class<PageObjectClass> pageObjectClass)
    {
        proxy.clickElement(getSelf());
        return page(pageObjectClass);
    }

    public PageObjectClass clickButtonAt(Class<PageObjectClass> pageObjectClass, int x, int y)
    {
        proxy.clickElementAt(getSelf(), x, y);
        return page(pageObjectClass);
    }

    public void checkButtonText(String txt)
    {
        proxy.checkText(getSelf(), txt);
    }
}
