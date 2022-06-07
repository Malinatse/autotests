package org.selenide.examples.containers.vgtables.vgscreeningquestions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.EditScreeningQuestionModal;

import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с одним радом таблицы User Management
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGRow extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "td")
    private ElementsCollection cells;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Получение порядкового номера вопроса
     * @return String elem
     */
    public String getNumber()
    {
        SelenideElement elem = cells.get(1).$x("div/div/div/div/span");
        return proxy.getText(elem);
    }

    /**
     * Проверка значения порядкового номера вопроса
     * @param numberTxt проверяемое значение
     */
    public void checkNumber(String numberTxt)
    {
        SelenideElement elem = cells.get(1).$x("div/div/div/div/span");
        proxy.checkText(elem, numberTxt);
    }

    /**
     * Получение текста вопроса
     * @return String imgTxt
     */
    public String getQuestionText()
    {
        SelenideElement elem = cells.get(1).$x("div/div/div/p");
        return proxy.getText(elem);
    }

    /**
     * Проверка текстового значения вопроса
     * @param qTxt проверяемое значение
     */
    public void checkQuestionText(String qTxt)
    {
        SelenideElement elem = cells.get(1).$x("div/div/div/p");
        proxy.checkText(elem, qTxt);
    }

    /**
     * Получение текста поля TYPE
     * @return String roleTxt
     */
    public String getType()
    {
        SelenideElement elem = cells.get(2).$x("div/div");
        return proxy.getText(elem);
    }

    /**
     * Проверка значения поля TYPE
     * @param typeTxt проверяемое значение
     */
    public void checkType(String typeTxt)
    {
        SelenideElement elem = cells.get(2).$x("div/div");
        proxy.checkText(elem, typeTxt);
    }

    /**
     * Нажатие на кнопку редактирования
     */
    public EditScreeningQuestionModal clickEdit()
    {
        SelenideElement elem = cells.get(3).$x("div/div/button");
        proxy.clickElement(elem);
        return page(EditScreeningQuestionModal.class);
    }

    /**
     * Нажатие на кнопку удаления
     */
    public void clickDelete()
    {
        SelenideElement elem = cells.get(4).$x("div/div/button");
        proxy.clickElement(elem);
    }

}
