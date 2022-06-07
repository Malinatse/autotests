package org.selenide.examples.containers.vgtables.vgteams;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.pages.TeamInfoPage;

import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с одним радом таблицы User Management
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGRow extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "td")
    private ElementsCollection cells;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Получение значения поля Name
     * @return String Name
     */
    public String getName()
    {
        SelenideElement href = cells.get(0).$x("span/a");
        return proxy.getText(href);
    }

    /**
     * Проверка значения поля Name
     * @param nameTxt проверяемое значение
     */
    public void checkName(String nameTxt)
    {
        SelenideElement href = cells.get(0).$x("span/a");
        proxy.checkText(href, nameTxt);
    }

    /**
     * ПНажатие на поле
     */
    public TeamInfoPage clickName()
    {
        SelenideElement href = cells.get(0).$x("span/a");
        proxy.clickElement(href);
        return page(TeamInfoPage.class);
    }

    /**
     * Получение значения поля Name
     * @return String Parent
     */
    public String getParent()
    {
        SelenideElement href = cells.get(1).$x("span/a");
        return proxy.getText(href);
    }

    /**
     * Проверка значения поля Name
     * @param parentTxt проверяемое значение
     */
    public void checkParent(String parentTxt)
    {
        SelenideElement href = cells.get(1).$x("span/a");
        proxy.checkText(href, parentTxt);
    }

    /**
     * Нажатие на поле Parent
     */
    public TeamInfoPage clickParent()
    {
        SelenideElement href = cells.get(1).$x("span/a");
        proxy.clickElement(href);
        return page(TeamInfoPage.class);
    }
}
