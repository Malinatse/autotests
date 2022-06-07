package org.selenide.examples.containers.vgtables.topicsandquestions;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с записями таблицы User Management
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGRows extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = ".//tr")
    public List<VGRow> tableRows;

    /**
     * Получение текстового значения указанной ячейки
     *
     * @param rowId искомая строка
     * @param field искомый столбец
     * @return String result
     */
    public String getTextCellData(Integer rowId, VGTable.TableFields field) {
        VGRow row;
        String retString;
        switch (field) {
            case ID:
                row = tableRows.get(rowId);
                return row.getId();
            case QUESTION:
                row = tableRows.get(rowId);
                return row.getQuestion();
            case TYPE:
                row = tableRows.get(rowId);
                return row.getType();
            default:
                return "Table field does not exists";

        }
    }

    /**
     * Проверка текстового значения указанной ячейки
     *
     * @param rowId искомая строка
     * @param field искомый столбец
     * @param txt   искомый текст
     */
    public void checkTextCellData(Integer rowId, VGTable.TableFields field, String txt) {
        VGRow row;
        switch (field) {
            case ID:
                row = tableRows.get(rowId);
                row.checkId(txt);
                break;
            case QUESTION:
                row = tableRows.get(rowId);
                row.checkQuestion(txt);
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

    public void clickCellDeleteButton(Integer rowId) {
        VGRow row;
        row = tableRows.get(rowId);
        row.clickDelete();
    }

    public CreateEditQuestionModal clickCellEditButton(Integer rowId) {
        VGRow row;
        row = tableRows.get(rowId);
        row.clickEdit();
        return page(CreateEditQuestionModal.class);
    }
}
