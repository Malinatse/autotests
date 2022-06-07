package org.selenide.examples.containers.vgtables.vgteammembers;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
                return row.getEmail();
            case IMAGE:
                row = tableRows.get(rowId);
                return row.getImage();
            case ROLE:
                row = tableRows.get(rowId);
                return row.getRole();
            case TEAM:
                row = tableRows.get(rowId);
                return row.getTeam();
            case LAST_ACTIVE:
                row = tableRows.get(rowId);
                return row.getLastActive();
            case STATUS:
                row = tableRows.get(rowId);
                return row.getStatus();
            case EXPIRATION:
                row = tableRows.get(rowId);
                return row.getExpiration();
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
                row.checkEmail(txt);
                break;
            case IMAGE:
                row = tableRows.get(rowId);
                row.checkImage(txt);
                break;
            case ROLE:
                row = tableRows.get(rowId);
                row.checkRole(txt);
                break;
            case TEAM:
                row = tableRows.get(rowId);
                row.checkTeam(txt);
                break;
            case LAST_ACTIVE:
                row = tableRows.get(rowId);
                row.checkLastActive(txt);
                break;
            case STATUS:
                row = tableRows.get(rowId);
                row.checkStatus(txt);
                break;
            case EXPIRATION:
                row = tableRows.get(rowId);
                row.checkExpiration(txt);
                break;
            default:
                /*noting to do*/
                break;
        }
    }
}
