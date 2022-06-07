package org.selenide.examples.baseelements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.html.primitives.IInput;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.getSelectedRadio;

/**
 * Объект для взаимодействия с полем выбора данных
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class SelectInput extends ElementsContainer implements IInput {

    // TODO add new xpath
    @FindBy(how = How.XPATH, xpath = "label")
    private SelenideElement selectInputHeader;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='SelectInputCaption']")
    private SelenideElement selectInputCaption;

    @FindBy(how = How.XPATH, xpath = ".//input")
    private SelenideElement selectInputField;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Проверка заголовка поля
     * @param txt искомый текст
     */
    public void checkSelectInputHeader(String txt)
    {
        proxy.checkText(selectInputHeader, txt);
    }

    /**
     * Установка значения для поля
     * @param selectTxt новое значение. Работает автодополнение после нажатие Enter
     * @param enterPress флаг нажатия клавиши Enter после ввода
     */
    public void setClearInput(String selectTxt, Boolean enterPress) {
        //proxy.setClearTextForInput(selectInputField, selectTxt, enterPress);
        proxy.clickElement(getSelf());
        //proxy.clickElement(getSelf());
        proxy.clickElement($x(".//div[contains(text(),\""+ selectTxt +"\")]"));

    }

    public void setInput(String selectTxt, Boolean enterPress) {
        proxy.setTextForInput(selectInputField, selectTxt, enterPress);
    }


    public void checkSelectSpan(String selectTxt) {
        proxy.checkText(selectInputCaption, selectTxt);
    }
}
