package org.selenide.examples.testdriver.pages;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.containers.vgtables.vgusermanagement.VGTable;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.AddUserModal;

/**
 * Объект для взаимодействия со страницей User Management
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class UserManagementPage extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "//button[contains(@class, 'LLAddLabel LLAddLabel_red')]")
    public Button<AddUserModal> addUsrBtn;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='userManagementTable']")
    private VGTable userTable;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='cookiesBtn']")
    private SelenideElement cookiesAccept;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Получение текстового значение поля NAME -> EMAIL в заданной строке таблицы
     * @param row номер строки
     * @return String userName
     */
    public String getUserName(Integer row)
    {
        return userTable.rows.getTextCellData(row, VGTable.TableFields.NAME);
    }

    /**
     * Проверка текстового значение поля NAME -> EMAIL в заданной строке таблицы
     * @param row номер строки
     * @param mailText проверяемая строка
     */
    public void checkUserName(Integer row, String mailText)
    {
        userTable.rows.checkTextCellData(row, VGTable.TableFields.NAME, mailText);
    }

    /**
     * Проверка текстового значение поля ROLE в заданной строке таблицы
     * @param row номер строки
     * @param roleText проверяемая строка
     */
    public void checkUserRole(Integer row, String roleText)
    {
        userTable.rows.checkTextCellData(row, VGTable.TableFields.ROLE, roleText);
    }

    /**
     * Проверка текстового значение поля TEAM в заданной строке таблицы
     * @param row номер строки
     * @param teamText проверяемая строка
     */
    public void checkUserTeam(Integer row, String teamText)
    {
        userTable.rows.checkTextCellData(row, VGTable.TableFields.TEAM, teamText);
    }

    /**
     * Проверка текстового значение поля LAST ACTIVE в заданной строке таблицы
     * @param row номер строки
     * @param lastActiveText проверяемая строка
     */
    public void checkUserLastActive(Integer row, String lastActiveText)
    {
        userTable.rows.checkTextCellData(row, VGTable.TableFields.LAST_ACTIVE, lastActiveText);
    }

    /**
     * Проверка текстового значение поля STATUS в заданной строке таблицы
     * @param row номер строки
     * @param statusText проверяемая строка
     */
    public void checkUserStatus(Integer row, String statusText)
    {
        userTable.rows.checkTextCellData(row, VGTable.TableFields.STATUS, statusText);
    }

    /**
     * Проверка текстового значение поля EXPIRATION в заданной строке таблицы
     * @param row номер строки
     * @param expirationText проверяемая строка
     */
    public void checkUserExpiration(Integer row, String expirationText)
    {
        userTable.rows.checkTextCellData(row, VGTable.TableFields.EXPIRATION, expirationText);
    }

    public void acceptCookies() {
        if (cookiesAccept.isDisplayed()){
            proxy.clickElementIfExist(cookiesAccept);
        }
    }

}
