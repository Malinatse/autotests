package org.selenide.examples.containers.vgtables.vgteams;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.testdriver.pages.TeamInfoPage;

import java.util.List;

/**
 * Объект для взаимодействия с записями таблицы User Management
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGRows extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "tr")
    private List<VGRow> tableRows;

    /**
     * Получение текстового значения указанной ячейки
     * @param rowId искомая строка
     * @param field искомый столбец
     * @return String result
     */
    public String getTextCellData(Integer rowId, VGTable.TableFields field)
    {
        
        VGRow row;
        String retString;
        switch (field) {
            case NAME:
                row = tableRows.get(rowId);
                return row.getName();
            case PARENT:
                row = tableRows.get(rowId);
                return row.getParent();
            default:
                    return "Table field does not exists";

        }
    }

    /**
     * Проверка текстового значения указанной ячейки
     * @param rowId искомая строка
     * @param field искомый столбец
     * @param txt искомый текст
     */
    public void checkTextCellData(Integer rowId, VGTable.TableFields field, String txt)
    {
        
        VGRow row;
        switch (field) {
            case NAME:
                row = tableRows.get(rowId);
                row.checkName(txt);
                break;
            case PARENT:
                row = tableRows.get(rowId);
                row.checkParent(txt);
                break;
            default:
                /*noting to do*/
                break;
        }
    }

    /**
     * Нажатие на ссылку ячейки
     * @param rowId искомая строка
     * @param field искомый столбец
     * @return String result
     */
    public TeamInfoPage clickTextCellData(Integer rowId, VGTable.TableFields field)
    {
        
        VGRow row;
        String retString;
        switch (field) {
            case NAME:
                row = tableRows.get(rowId);
                return row.clickName();
            case PARENT:
                row = tableRows.get(rowId);
                return row.clickParent();
            default:
                //todo: Raise Exception
                return null;

        }
    }
}
