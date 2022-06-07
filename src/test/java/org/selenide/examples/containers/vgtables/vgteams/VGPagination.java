package org.selenide.examples.containers.vgtables.vgteams;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с элементамии пагинации
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGPagination extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = "div[1]/span")
    private SelenideElement rowsTxt;

    @FindBy(how = How.XPATH, xpath = "div[1]/select")
    private SelenideElement rowsPerPage;

    @FindBy(how = How.XPATH, xpath = "div[2]/a[1]")
    private SelenideElement prev;

    @FindBy(how = How.XPATH, xpath = "div[2]/a[2]")
    private SelenideElement next;

    @FindBy(how = How.XPATH, xpath = "div[2]/div")
    private SelenideElement pagesShown;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Нажатие на кнопку Next
     */
    public void clickNext() {
        proxy.clickElement(next);
    }

    /**
     * Нажатие на кнопку Prev
     */
    public void clickPrev() {
        proxy.clickElement(prev);
    }

    /**
     * Получение списка отображенных страниц (пагинация)
     *
     * @return String reult
     */
    public String getPagesShown() {
        return proxy.getText(pagesShown);
    }

    /**
     * Выбор количества отображаемых страниц по возможному значению
     *
     * @param value значение, в которое нужно установить
     */
    public void selectRowsPerPage(String value) {
        proxy.setSelectByText(rowsPerPage, value);
    }


}
