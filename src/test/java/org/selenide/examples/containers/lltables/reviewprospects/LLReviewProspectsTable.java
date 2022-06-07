package org.selenide.examples.containers.lltables.reviewprospects;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.testdriver.modals.ProspectDetailsModal;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Контейнер элементов таблицы User Management
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class LLReviewProspectsTable extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='tableRow']")
    private LLRow rowsHelper;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='tableRow']")
    private LLColumn columnsHelper;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='prospectTableHeader']")
    public LLCustomElements buttonsBar;

    public void checkCellValue(String columnName, int rowId, String value)
    {
        columnName = columnName.toLowerCase(Locale.ROOT);
        rowsHelper.checkCell(columnName, value, rowId);
    }

    public void clickCell(String columnName, int rowId)
    {
        columnName = columnName.toLowerCase(Locale.ROOT);
        rowsHelper.clickCell(columnName, rowId);
    }

    public ProspectDetailsModal clickName(String columnName, int rowId)
    {
        columnName = columnName.toLowerCase(Locale.ROOT);
        rowsHelper.clickName(columnName, rowId);
        return page(ProspectDetailsModal.class);
    }

    public void setInvitationState(String state, int rowId)
    {
        rowsHelper.clickSelectorInCell("invitationStateSelector", rowId);
        // $x("(//div[contains(text(), \"" + state + "\")])[" + rowId + 1 + "]").click();
        WebElement element = getWebDriver().findElement(By.xpath("(//div[contains(text(), \"" + state + "\")])[" + (rowId + 1) + "]"));
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(element).click().perform();
    }

    public void setApprovalStatus(String state, int rowId)
    {
        rowsHelper.clickSelectorInCell("approvalStateSelector", rowId);
        // $x("(//div[contains(text(), \"" + state + "\")])[" + rowId + 1 + "]").click();
        WebElement element = getWebDriver().findElement(By.xpath("(//div[contains(text(), \"" + state + "\")])[" + rowId + 1 + "]"));
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(element).click().perform();
    }

    public void clickCheckBox(String tableName, int rowId)
    {
        rowsHelper.clickCheckBox(tableName, rowId);
    }

    public void checkColumnValue(String tableName, String columnName, String value)
    {
        columnName = columnName.toLowerCase(Locale.ROOT);
        columnsHelper.checkHeader(tableName, columnName, value);
    }

    public void clickColumn(String tableName, String columnName)
    {
        columnName = columnName.toLowerCase(Locale.ROOT);
        columnsHelper.clickHeader(tableName, columnName);
    }

    /**
     * Список доступных столбцов
     */
    public enum TableFields {
        NAME,
        AGE,
        GENDER,
        ETHNICITY,
        LOCATION
    }
}
