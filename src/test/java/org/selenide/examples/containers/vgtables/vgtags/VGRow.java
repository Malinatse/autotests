package org.selenide.examples.containers.vgtables.vgtags;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с одним рядом таблицы Add Tags
 * @author
 * @version 1.0
 * @since 1.0
 */
public class VGRow extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "td")
    private ElementsCollection cells;

    @FindBy(how = How.XPATH, xpath = "th")
    private SelenideElement cellHead;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Получение значения поля Name
     * @return String
     */
    public String getName()
    {
        SelenideElement item = cells.get(0).$x("button/div/span");
        return proxy.getText(item);
    }

    /**
     * Проверка значения поля Name
     * @param txt проверяемое значение
     */
    public void checkName(String txt)
    {
        SelenideElement item = cells.get(0).$x("button/div/span");
        proxy.checkText(item, txt);
    }

    /**
     * Нажатие на кнопку удаления
     */
    public void clickDelete()
    {
        SelenideElement item = cells.get(8).$x("svg");
        proxy.clickElement(item);
    }

    /**
     * Нажатие на чекбокс
     */
    public void clickChecker()
    {
        proxy.clickElement(cellHead);
    }

    /**
     * Нажатие на участника
     */
    public void clickParticipant()
    {
        SelenideElement item = cells.get(0).$x("button/div/span");
        proxy.clickElement(item);
    }
}
