package org.selenide.examples.containers.vgtables.topicsandquestions;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Объект для взаимодействия с заголовками таблицы User Management
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGHeaders extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "th")
    private List<VGHeader> headersRows;

    /**
     * Возвращает текст заголовка в зависимости от его порядкового номера в таблице
     *
     * @param id Integer - порядковый номер
     * @return String текст заголовка
     */
    public String getHeaderText(Integer id) {
        if ((id < 0) || (id > headersRows.size())) {
            return "Id is out of range. Id is" + id.toString() + " and it's max may lay between 0 and "
                    + ((Integer) headersRows.size()).toString();
        }
        VGHeader item = headersRows.get(id);
        return item.getName();
    }
}
