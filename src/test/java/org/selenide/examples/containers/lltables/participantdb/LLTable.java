package org.selenide.examples.containers.lltables.participantdb;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Контейнер элементов таблицы User Management
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class LLTable extends ElementsContainer {

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
        $x("//*[contains(@class, \"LLTable_loading\")]").shouldNot(Condition.visible, Duration.ofSeconds(10));
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
