package org.selenide.examples.containers.vgtables.vgtags;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.TextInput;

/**
 * Контейнер элементов таблицы Add Tags
 * @author .
 * @version 1.0
 * @since 1.0
 */
public class VGTable extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='tagSearch']")
    public TextInput tagSearch;

    @FindBy(how = How.XPATH, xpath = "div[2]/div/div[2]/table/thead/tr")
    public VGHeaders headers;

    @FindBy(how = How.XPATH, xpath = "div[2]/div/div[2]/table/tbody")
    public VGRows rows;

    @FindBy(how = How.XPATH, xpath = "div[2]/div/div[4]")
    public VGPagination pagination;

    /**
     * Список доступных столбцов
     */
    public enum TableFields {
        TAG_NAME,
        TIMES_USED,
        CONTENT_TYPE,
        CREATED_BY,
        ACTIONS
    }

    public enum TableFieldsActions {
        CLICK_CHECKBOX,
        DELETE,
        NAME_CLICK
    }
}
