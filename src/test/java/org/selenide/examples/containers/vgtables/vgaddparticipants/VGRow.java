package org.selenide.examples.containers.vgtables.vgaddparticipants;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с одним рядом таблицы Add Participants
 * @author Fedosov I.
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
     * Получение значения поля Gender
     * @return String
     */
    public String getGender()
    {
        SelenideElement item = cells.get(1).$x("div/p");
        return proxy.getText(item);
    }

    /**
     * Проверка значения поля Gender
     * @param txt проверяемое значение
     */
    public void checkGender(String txt)
    {
        SelenideElement item = cells.get(1).$x("div/p");
        proxy.checkText(item, txt);
    }

    /**
     * Получение значения поля Age
     * @return String
     */
    public String getAge()
    {
        SelenideElement item = cells.get(2).$x("div/p");
        return proxy.getText(item);
    }

    /**
     * Проверка значения поля Age
     * @param txt проверяемое значение
     */
    public void checkAge(String txt)
    {
        SelenideElement item = cells.get(2).$x("div/p");
        proxy.checkText(item, txt);
    }

    /**
     * Получение значения поля Studies
     * @return String
     */
    public String getStudies()
    {
        SelenideElement item = cells.get(3).$x("div/p");
        return proxy.getText(item);
    }

    /**
     * Проверка значения поля Studies
     * @param txt проверяемое значение
     */
    public void checkStudies(String txt)
    {
        SelenideElement item = cells.get(3).$x("div/p");
        proxy.checkText(item, txt);
    }

    /**
     * Получение значения поля Superstar
     * @return String
     */
    public String getSuperstar()
    {
        SelenideElement item = cells.get(4).$x("div/p");
        return proxy.getText(item);
    }

    /**
     * Проверка значения поля Superstar
     * @param txt проверяемое значение
     */
    public void checkSuperstar(String txt)
    {
        SelenideElement item = cells.get(4).$x("div/p");
        proxy.checkText(item, txt);
    }

    /**
     * Получение значения поля LuxQualified
     * @return String
     */
    public String getLuxQualified()
    {
        SelenideElement item = cells.get(5).$x("div/p");
        return proxy.getText(item);
    }

    /**
     * Проверка значения поля LuxQualified
     * @param txt проверяемое значение
     */
    public void checkLuxQualified(String txt)
    {
        SelenideElement item = cells.get(5).$x("div/p");
        proxy.checkText(item, txt);
    }

    /**
     * Получение значения поля HHI
     * @return String
     */
    public String getHHI()
    {
        SelenideElement item = cells.get(6).$x("div/p");
        return proxy.getText(item);
    }

    /**
     * Проверка значения поля HHI
     * @param txt проверяемое значение
     */
    public void checkHHI(String txt)
    {
        SelenideElement item = cells.get(6).$x("div/p");
        proxy.checkText(item, txt);
    }

    /**
     * Получение значения поля Location
     * @return String
     */
    public String getLocation()
    {
        SelenideElement item = cells.get(7).$x("div/p");
        return proxy.getText(item);
    }

    /**
     * Проверка значения поля Location
     * @param txt проверяемое значение
     */
    public void checkLocation(String txt)
    {
        SelenideElement item = cells.get(7).$x("div/p");
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
