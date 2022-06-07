package org.selenide.examples.baseelements.questiontypes;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInputWithDel;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

/**
 * Объект для взаимодействия с полем многовариантного ответа
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class MultipleChoiceQuestion extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//p")
    public TextProperty inputsListHeader;

    @FindBy(how = How.XPATH, xpath = ".//a")
    public Button addButton;

    @FindBy(how = How.XPATH, xpath = ".//input/parent::div/parent::div/parent::div")
    private List<TextInputWithDel> inputListAnswers;

    /**
     * Установка значения указанному ответу
     * @param txt устанавливаемое значение
     * @param id номер поля ввода ответа
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setAnswer(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > inputListAnswers.size()))
        {
            //TODO:
            // Raise exception
        }
        TextInputWithDel input = inputListAnswers.get(id);
        input.setInput(txt, enterPress);
    }

    /**
     * Установка значения указанному ответу после предварительной очистки содержимого
     * @param txt устанавливаемое значение
     * @param id номер поля ввода ответа
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setClearAnswer(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > inputListAnswers.size()))
        {
            //TODO:
            // Raise exception
        }
        TextInputWithDel input = inputListAnswers.get(id);
        input.setClearInput(txt, enterPress);
    }

    /**
     * Проверка содержимого поля ввода ответа
     * @param txt проверяемый текст
     * @param id номер поля ввода ответа
     */
    public void checkClearAnswer(String txt, Integer id)
    {
        if ((id < 0) || (id > inputListAnswers.size()))
        {
            //TODO:
            // Raise exception
        }
        TextInputWithDel input = inputListAnswers.get(id);
        input.checkInputValue(txt);
    }

    /**
     * Удаление поля ввода
     * @param id номер поля ввода ответа
     */
    public void clickDeleteInput(Integer id)
    {
        if ((id < 0) || (id > inputListAnswers.size()))
        {
            //TODO:
            // Raise exception
        }
        TextInputWithDel input = inputListAnswers.get(id);
        input.clickDelete();
    }
}
