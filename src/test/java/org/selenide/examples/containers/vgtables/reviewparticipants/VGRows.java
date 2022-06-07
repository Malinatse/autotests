package org.selenide.examples.containers.vgtables.reviewparticipants;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.testdriver.modals.ProspectDetailsModal;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с записями таблицы Review Participants
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
     * @param columnId искомый столбец
     * @return String result
     */
    public String getTextCellData(Integer columnId, Integer rowId)
    {
        
        VGRow row;
        row = tableRows.get(rowId);
        return row.getCellText(columnId);
    }

    /**
     * Проверка текстового значения указанной ячейки
     * @param rowId искомая строка
     * @param columnId искомый столбец
     * @param txt искомый текст
     */
    public void checkTextCellData(Integer columnId, Integer rowId, String txt)
    {
        
        VGRow row;
        row = tableRows.get(rowId);
        row.checkCellText(columnId, txt);
    }

    /**
     * Нажатие на активный элемент указанной ячейки
     * @param rowId искомая строка
     * @param columnId искомый столбец
     */
    public void clickCellData(Integer columnId, Integer rowId)
    {
        
        VGRow row;
        row = tableRows.get(rowId);
        row.clickCell(columnId);
    }

    public ProspectDetailsModal clickProspectInfo(Integer rowId)
    {
        
        VGRow row;
        row = tableRows.get(rowId);
        row.clickProspect();
        return page(ProspectDetailsModal.class);
    }
}
