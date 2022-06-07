package org.selenide.examples.containers.vgtables.previewtable;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

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
    private ElementsCollection cells;

    /**
     * Получение значения поля Participants
     *
     * @return String Participants
     */
    public String getParticipants() {
        SelenideElement cell = cells.get(0).$x("div/p");
        return proxy.getText(cell);
    }

    /**
     * Проверка значения поля Participants
     *
     * @param text проверяемое значение
     */
    public void checkParticipants(String text) {
        SelenideElement cell = cells.get(0).$x("div/p");
        proxy.checkText(cell, text);
    }

    /**
     * Получение значения поля Groups
     *
     * @return String Groups
     */
    public String getGroups() {
        SelenideElement cell = cells.get(1).$x("div/p");
        return proxy.getText(cell);
    }

    /**
     * Проверка значения поля Groups
     *
     * @param text проверяемое значение
     */
    public void checkGroups(String text) {
        SelenideElement cell = cells.get(1).$x("div/p");
        proxy.checkText(cell, text);
    }

    /**
     * Получение значения поля Moderator
     *
     * @return String Moderator
     */
    public String getModerator() {
        SelenideElement cell = cells.get(2).$x("div/p");
        return proxy.getText(cell);
    }

    /**
     * Проверка значения поля Moderator
     *
     * @param text проверяемое значение
     */
    public void checkModerator(String text) {
        SelenideElement cell = cells.get(2).$x("div/p");
        proxy.checkText(cell, text);
    }

    /**
     * Получение значения поля Posts
     *
     * @return String Posts
     */
    public String getPosts() {
        SelenideElement cell = cells.get(3).$x("div/p");
        return proxy.getText(cell);
    }

    /**
     * Проверка значения поля Posts
     *
     * @param text проверяемое значение
     */
    public void checkPosts(String text) {
        SelenideElement cell = cells.get(3).$x("div/p");
        proxy.checkText(cell, text);
    }

    /**
     * Получение значения поля GroupPics
     *
     * @return String GroupPics
     */
    public String getGroupPics() {
        SelenideElement cell = cells.get(4).$x("div/p");
        return proxy.getText(cell);
    }

    /**
     * Проверка значения поля GroupPics
     *
     * @param text проверяемое значение
     */
    public void checkGroupPics(String text) {
        SelenideElement cell = cells.get(4).$x("div/p");
        proxy.checkText(cell, text);
    }

    /**
     * Получение значения поля GroupVideos
     *
     * @return String GroupVideos
     */
    public String getGroupVideos() {
        SelenideElement cell = cells.get(5).$x("div/p");
        return proxy.getText(cell);
    }

    /**
     * Проверка значения поля GroupVideos
     *
     * @param text проверяемое значение
     */
    public void checkGroupVideos(String text) {
        SelenideElement cell = cells.get(5).$x("div/p");
        proxy.checkText(cell, text);
    }

    /**
     * Получение значения поля CompletedQuestions
     *
     * @return String CompletedQuestions
     */
    public String getCompletedQuestions() {
        SelenideElement cell = cells.get(6).$x("div/p");
        return proxy.getText(cell);
    }

    /**
     * Проверка значения поля CompletedQuestions
     *
     * @param text проверяемое значение
     */
    public void checkCompletedQuestions(String text) {
        SelenideElement cell = cells.get(6).$x("div/p");
        proxy.checkText(cell, text);
    }

}
