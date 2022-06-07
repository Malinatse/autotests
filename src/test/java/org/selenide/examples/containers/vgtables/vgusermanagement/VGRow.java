package org.selenide.examples.containers.vgtables.vgusermanagement;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

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
     * Получение значения поля email
     * @return String mail
     */
    public String getEmail()
    {
        SelenideElement href = cells.get(0).$x("div/a");
        return proxy.getText(href);
    }

    /**
     * Проверка значения поля email
     * @param mailTxt проверяемое значение
     */
    public void checkEmail(String mailTxt)
    {
        SelenideElement href = cells.get(0).$x("div/a");
        proxy.checkText(href, mailTxt);
    }

    /**
     * Получение текста картинки
     * @return String imgTxt
     */
    public String getImage()
    {
        SelenideElement img = cells.get(0).$x("div/img");
        return proxy.getText(img);
    }

    /**
     * Проверка значения поля image
     * @param imgTxt проверяемое значение
     */
    public void checkImage(String imgTxt)
    {
        SelenideElement img = cells.get(0).$x("div/a");
        proxy.checkText(img, imgTxt);
    }

    /**
     * Получение текста поля Role
     * @return String roleTxt
     */
    public String getRole()
    {
        SelenideElement role = cells.get(1).$x("div/div");
        return proxy.getText(role);
    }

    /**
     * Проверка значения поля Role
     * @param roleTxt проверяемое значение
     */
    public void checkRole(String roleTxt)
    {
        SelenideElement role = cells.get(1).$x("div/div");
        proxy.checkText(role, roleTxt);
    }

    /**
     * Получение текста поля Team
     * @return String teamTxt
     */
    public String getTeam()
    {
        SelenideElement team = cells.get(2).$x("div/div/a");
        return proxy.getText(team);
    }

    /**
     * Проверка значения поля Team
     * @param teamTxt проверяемое значение
     */
    public void checkTeam(String teamTxt)
    {
        SelenideElement team = cells.get(2).$x("div/div/a");
        proxy.checkText(team, teamTxt);
    }

    /**
     * Получение текста поля Last Active
     * @return String laTxt
     */
    public String getLastActive()
    {
        SelenideElement lastActive = cells.get(3).$x("div/div/p");
        if (lastActive.exists())
            return proxy.getText(lastActive);
        else
            return "";
    }

    /**
     * Проверка значения поля Last Active
     * @param laTxt проверяемое значение
     */
    public void checkLastActive(String laTxt)
    {
        SelenideElement lastActive = cells.get(3).$x("div/div/p");
        if (lastActive.exists())
            proxy.checkText(lastActive, laTxt);
        else
            proxy.checkExistence(lastActive, false);
    }

    /**
     * Получение текста поля Status
     * @return String statusTxt
     */
    public String getStatus()
    {
        SelenideElement status = cells.get(4).$x("div/div");
        return proxy.getText(status);
    }

    /**
     * Проверка значения поля Status
     * @param statusTxt проверяемое значение
     */
    public void checkStatus(String statusTxt)
    {
        SelenideElement status = cells.get(4).$x("div/div");
        proxy.checkText(status, statusTxt);
    }

    /**
     * Получение текста поля Expiration
     * @return String expirationTxt
     */
    public String getExpiration()
    {
        SelenideElement expiration = cells.get(5).$x("div/div");
        return proxy.getText(expiration);
    }

    /**
     * Проверка значения поля Expiration
     * @param expirationTxt проверяемое значение
     */
    public void checkExpiration(String expirationTxt)
    {
        SelenideElement expiration = cells.get(5).$x("div/div");
        proxy.checkText(expiration, expirationTxt);
    }
}
