package org.selenide.examples.containers.vgtables.topicsandquestions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с одним радом таблицы User Management
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGRow extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());
    @FindBy(how = How.XPATH, xpath = "td")
    public ElementsCollection cells;

    /**
     * Получение значения поля Participants
     *
     * @return String Participants
     */
    public String getId() {
        SelenideElement cell = cells.get(0).$x("div");
        return proxy.getText(cell);
    }

    /**
     * Проверка значения поля Participants
     *
     * @param text проверяемое значение
     */
    public void checkId(String text) {
        SelenideElement cell = cells.get(0).$x("div");
        proxy.checkText(cell, text);
    }

    /**
     * Получение значения поля Groups
     *
     * @return String Groups
     */
    public String getQuestion() {
        SelenideElement cell = cells.get(1).$x("div");
        return proxy.getText(cell);
    }

    /**
     * Проверка значения поля Groups
     *
     * @param text проверяемое значение
     */
    public void checkQuestion(String text) {
        SelenideElement cell = cells.get(1).$x("div");
        proxy.checkText(cell, text);
    }

    /**
     * Получение значения поля Moderator
     *
     * @return String Moderator
     */
    public String getType() {
        SelenideElement cell = cells.get(2).$x("div");
        return proxy.getText(cell);
    }

    /**
     * Проверка значения поля Moderator
     *
     * @param text проверяемое значение
     */
    public void checkType(String text) {
        SelenideElement cell = cells.get(2).$x("div");
        proxy.checkText(cell, text);
    }


    public void clickEdit() {
        SelenideElement editBtn = cells.get(3).$x(".//*[@data-e2e=\"editQuestionBtn\"]");
        proxy.clickElement(editBtn);
    }


    public void clickDelete() {
        SelenideElement delBtn = cells.get(3).$x(".//*[@data-e2e=\"deleteQuestionBtn\"]");
        proxy.clickElement(delBtn);
    }


}
