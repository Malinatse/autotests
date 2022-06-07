package org.selenide.examples.containers.vgtables.vgscreeningquestions;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Объект для взаимодействия с записями таблицы Screening questions
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
            case NUMBER:
                row = tableRows.get(rowId);
                return row.getNumber();
            case QUESTION:
                row = tableRows.get(rowId);
                return row.getQuestionText();
            case TYPE:
                row = tableRows.get(rowId);
                return row.getType();
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
            case NUMBER:
                row = tableRows.get(rowId);
                row.checkNumber(txt);
                break;
            case QUESTION:
                row = tableRows.get(rowId);
                row.checkQuestionText(txt);
                break;
            case TYPE:
                row = tableRows.get(rowId);
                row.checkType(txt);
                break;
            default:
                /*noting to do*/
                break;
        }
    }

    private void clickImageCellData(Integer rowId, VGTable.ImgTableFields field)
    {
        
        VGRow row;
        switch (field) {
            case EDIT:
                row = tableRows.get(rowId);
                row.clickEdit();
                break;
            case DELETE:
                row = tableRows.get(rowId);
                row.clickDelete();
                break;
            default:
                /*nothing to do*/

        }
    }
}
