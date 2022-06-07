package org.selenide.examples.containers.vgtables.reviewparticipants;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.ProspectDetailsModal;

import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с одним рядом таблицы Review Participants
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGRow extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "td")
    private ElementsCollection cells;

    @FindBy(how = How.XPATH, xpath = "th")
    private SelenideElement cellHead;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Получение элемента управления ячейкой
     * @param columnId искомый столбец
     * @return SelenideElement
     */
    private SelenideElement getCell(Integer columnId)
    {
        if ((columnId<0) || (columnId > cells.size()))
        {
            //TODO:
            // raise exception
        }
        SelenideElement item;
        switch (columnId)
        {
            case 0:
                item = cellHead.$x("input");
                break;
            // Name
            case 1:
                item = cells.get(columnId).$x("div/div/span");
                break;
            // Status
            case 2:
                item = cells.get(columnId).$x("div/div");
                break;
            // Group
            case 3:
                item = cells.get(columnId).$x("div/div/div/span");
                break;
            // Email
            case 4:
                item = cells.get(columnId).$x("span");
                break;
            // Phone
            case 5:
                item = cells.get(columnId).$x("span");
                break;
            // Gender
            case 6:
                item = cells.get(columnId).$x("div/p");
                break;
            // Age
            case 7:
                item = cells.get(columnId).$x("div/p");
                break;
            // Location
            case 8:
                item = cells.get(columnId).$x("div/p");
                break;
            // Recruiter notes
            case 9:
                item = cells.get(columnId).$x("div/p");
                break;
            // Other
            default:
                item = cells.get(columnId).$x("span");
                break;
        }
        return item;
    }

    /**
     * Получение значения поля по заданному Id
     * @param columnId искомый столбец
     * @return String
     */
    public String getCellText(Integer columnId)
    {
        SelenideElement item = getCell(columnId);
        return proxy.getText(item);
    }

    /**
     * Проверка значения поля по заданному Id
     * @param columnId искомый столбец
     * @param txt проверяемый текст
     */
    public void checkCellText(Integer columnId, String txt)
    {
        SelenideElement item = getCell(columnId);
        proxy.checkText(item, txt);
    }

    /**
     * Нажатие на полде по заданному Id
     * @param columnId искомый столбец
     */
    public void clickCell(Integer columnId)
    {
        SelenideElement item = getCell(columnId);
        proxy.clickElement(item);
    }

    public ProspectDetailsModal clickProspect()
    {
        SelenideElement item = cells.get(0).$x("div/div/span");
        proxy.clickElement(item);
        return page(ProspectDetailsModal.class);
    }
}
