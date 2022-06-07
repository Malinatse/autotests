package org.selenide.examples.containers.vgtables.vgaddparticipants;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Объект для взаимодействия с записями таблицы Add Participants
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
        switch (field) {
            case NAME:
                row = tableRows.get(rowId);
                return row.getName();
            case GENDER:
                row = tableRows.get(rowId);
                return row.getGender();
            case AGE:
                row = tableRows.get(rowId);
                return row.getAge();
            case STUDIES:
                row = tableRows.get(rowId);
                return row.getStudies();
            case SUPERSTAR:
                row = tableRows.get(rowId);
                return row.getSuperstar();
            case LUX_QUALIFIED:
                row = tableRows.get(rowId);
                return row.getLuxQualified();
            case HHI:
                row = tableRows.get(rowId);
                return row.getHHI();
            case LOCATION:
                row = tableRows.get(rowId);
                return row.getLocation();
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
            case GENDER:
                row = tableRows.get(rowId);
                row.checkGender(txt);
                break;
            case AGE:
                row = tableRows.get(rowId);
                row.checkAge(txt);
                break;
            case STUDIES:
                row = tableRows.get(rowId);
                row.checkStudies(txt);
                break;
            case SUPERSTAR:
                row = tableRows.get(rowId);
                row.checkSuperstar(txt);
                break;
            case LUX_QUALIFIED:
                row = tableRows.get(rowId);
                row.checkLuxQualified(txt);
                break;
            case HHI:
                row = tableRows.get(rowId);
                row.checkHHI(txt);
                break;
            case LOCATION:
                row = tableRows.get(rowId);
                row.checkLocation(txt);
                break;
            default:
                /*noting to do*/
                break;
        }
    }

    /**
     * Нажатие на активный элемент указанной ячейки
     * @param rowId искомая строка
     * @param field искомый столбец
     */
    public void clickImageCellData(Integer rowId, VGTable.TableFieldsActions field)
    {
        
        VGRow row;
        switch (field) {
            case NAME_CLICK:
                row = tableRows.get(rowId);
                row.clickParticipant();
                break;
            case DELETE:
                row = tableRows.get(rowId);
                row.clickDelete();
                break;
            case CLICK_CHECKBOX:
                row = tableRows.get(rowId);
                row.clickChecker();
                break;
            default:
                /*nothing to do*/

        }
    }
}
