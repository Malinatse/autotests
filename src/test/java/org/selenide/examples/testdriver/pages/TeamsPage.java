package org.selenide.examples.testdriver.pages;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.containers.vgtables.vgteams.VGTable;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.AddTeamModal;

import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с записями таблицы User Management
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class TeamsPage extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='teamTbl']")
    private VGTable userTable;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addTeamBtn']/button")
    public Button<AddTeamModal> addTeamBtn;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Проверка текстового значение поля NAME в заданной строке таблицы
     * @param row номер строки
     * @param name проверяемая строка
     */
    public void checkTeamName(Integer row, String name)
    {
        userTable.rows.checkTextCellData(row, VGTable.TableFields.NAME, name);
    }

    /**
     * Нажатие на текстовое значение поля NAME в заданной строке таблицы
     * @param row номер строки
     */
    public TeamInfoPage clickTeamName(Integer row)
    {
        userTable.rows.clickTextCellData(row, VGTable.TableFields.NAME);
        return page(TeamInfoPage.class);

    }

    /**
     * Проверка текстового значение поля Parent в заданной строке таблицы
     * @param row номер строки
     * @param parent проверяемая строка
     */
    public void checkTeamParent(Integer row, String parent)
    {
        userTable.rows.checkTextCellData(row, VGTable.TableFields.PARENT, parent);
    }

    /**
     * Нажатие на текстовое значение поля Parent в заданной строке таблицы
     * @param row номер строки
     */
    public TeamInfoPage clickTeamParent(Integer row)
    {
        userTable.rows.clickTextCellData(row, VGTable.TableFields.PARENT);
        return page(TeamInfoPage.class);
    }

}
